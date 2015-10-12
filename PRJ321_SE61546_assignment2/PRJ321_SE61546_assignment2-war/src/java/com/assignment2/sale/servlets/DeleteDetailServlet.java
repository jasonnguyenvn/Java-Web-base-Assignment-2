/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sale.servlets;

import com.assignment2.sale.OrderDetailDeleteError;
import com.assignment2.sessionBeans.OrderDetailSessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau
 */
public class DeleteDetailServlet extends HttpServlet {
    private final String erroPage = "/actions/ViewDetailServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String orderID = request.getParameter("orderID");
        String txtFromDate = request.getParameter("txtFromDate");
        String txtToDate = request.getParameter("txtToDate");
        String pk = request.getParameter("ID");
        int id = -1;
        try {
            id = Integer.parseInt(pk);
        } catch (NumberFormatException ex) {
            response.sendError(404);
        }
        
        if (id < 0) {
            response.sendError(404);
            return ;
        }
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        String loginID = (String) session.getAttribute("LOGINUSER");
        if (loginID == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }
        
        try {
            Context context = new InitialContext();
            Object obj = context.lookup("OrderDetailBeanLocalJNDI");
            
            OrderDetailSessionBeanLocal poji = (OrderDetailSessionBeanLocal) obj;
            boolean result = poji.deleteOrderDetail(id, orderID, loginID);
            
            if (result == false ) {
                OrderDetailDeleteError errorObj = new OrderDetailDeleteError();
                errorObj.setCouldNotDeleteOrderDetail("Cannot delete this item. "
                        + "Please call software developer to sovle this problem.");
                request.setAttribute("ERROROBJ", errorObj);
                RequestDispatcher dr = request.getRequestDispatcher(erroPage);
                dr.forward(request, response);
                return;
            }
            
            String urlRewriting = "ProcessServlet?btAction=view_detail&orderID="
                                    + orderID;
            
            if (txtFromDate != null && txtToDate != null) {
                urlRewriting += "&txtFromDate=" + txtFromDate 
                        + "&txtToDate=" + txtToDate;
            }
            
            response.sendRedirect(urlRewriting);
            
            
        } catch (NamingException e) {
            log(e.getMessage());
            response.sendError(500);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
