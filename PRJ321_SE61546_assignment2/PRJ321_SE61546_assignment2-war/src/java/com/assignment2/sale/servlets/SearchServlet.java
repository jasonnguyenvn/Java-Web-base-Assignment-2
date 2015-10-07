/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sale.servlets;

import com.assignment2.sale.OrderSearchError;
import com.assignment2.sessionBeans.OrderSessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hau
 */
public class SearchServlet extends HttpServlet {
    private final String searchPage = "/views/search.jsp";

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
        String txtFromDate = request.getParameter("txtFromDate");
        String txtToDate = request.getParameter("txtToDate");
        
        OrderSearchError searchErrorObj = new OrderSearchError();
        
        Date fromDate = null, toDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            fromDate = dateFormat.parse(txtFromDate);
        } catch (ParseException ex) {
            log("User input invlaid from date format.");
            searchErrorObj.setInvalidFromDateFormatErr(("Invalid from date format"));
        }
        try {
            toDate = dateFormat.parse(txtToDate);
        } catch (ParseException ex) {
            log("User input invlaid to date format.");
            searchErrorObj.setInvalidFromDateFormatErr(("Invalid to date format"));
        }
        
        
        try {
            if (toDate.compareTo(fromDate) < 0) {
                searchErrorObj.setToDateEalierThanFromDateErr("To Date cannot "
                        + "be ealier than From Date!");
            }
        } catch (NullPointerException ex) {
            log("Some user sends bad request to this action.");
            response.sendError(404);
            return;
        }
        
        if (searchErrorObj.isRaisedErrors()) {
            request.setAttribute("SEARCHERROROBJ", searchErrorObj);
            request.getRequestDispatcher(searchPage).forward(request, response);
            return;
        }
        
        try {
            Context context = new InitialContext();
            
            Object obj = context.lookup("OrderBeanLocalJNDI");
            OrderSessionBeanLocal poji = (OrderSessionBeanLocal) obj;
            
            List result = poji.searchFromDateToDate(null, null);
            
            request.setAttribute("ORDERLIST", obj);
            RequestDispatcher rd = request.getRequestDispatcher(searchPage);
            rd.forward(request, response);
            
        } catch (NamingException ex) {
            log(ex.getMessage());
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
