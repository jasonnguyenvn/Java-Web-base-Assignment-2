/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sale.servlets;

import com.assignment2.entityBeans.Account;
import com.assignment2.entityBeans.OrderEntity;
import com.assignment2.sessionBeans.AccountSessionBeanLocal;
import com.assignment2.sessionBeans.OrderDetailSessionBeanLocal;
import com.assignment2.sessionBeans.OrderSessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class ViewDetailServlet extends HttpServlet {
    private final String detailPage = "/views/OrderDetails.jsp";

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
        
        try {
            Context context = new InitialContext();
            
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendError(404);
                return ;
            }
            
            String loginID = (String) session.getAttribute("LOGINUSER");
            if (loginID == null) {
                response.sendError(404);
                return ;
            }
            Object accObj = context.lookup("AccountBeanLocalJNDI");
            AccountSessionBeanLocal accPoji = (AccountSessionBeanLocal) accObj;
            
            Account orderCust = accPoji.getAccountByAccountID(loginID);
            if (orderCust == null) {
                response.sendError(404);
                return ;
            }
            
            //log("[LOG]: "+ orderID + " " + orderCust.getAccountID());
            Object obj = context.lookup("OrderBeanLocalJNDI");
            OrderSessionBeanLocal poji = (OrderSessionBeanLocal) obj;
            OrderEntity order = poji.getOrderByIDAndCustID(orderID, orderCust.getAccountID());
            
            if (order == null) {
                response.sendError(404);
                return ;
            }
            
            
            
            Object obj2 = context.lookup("OrderDetailBeanLocalJNDI");
            OrderDetailSessionBeanLocal poji2 = (OrderDetailSessionBeanLocal) obj2;

            List detailList = poji2.getrDetailsByOrderID(orderID);

            request.setAttribute("ORDERCUST", orderCust);
            request.setAttribute("ORDEROBJ", order);
            request.setAttribute("ITEMLIST", detailList);
            
            RequestDispatcher rd = request.getRequestDispatcher(detailPage);
            rd.forward(request, response);
            
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
