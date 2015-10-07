/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.account.servlets;

import com.assignment2.account.AccountInsertError;
import com.assignment2.sessionBeans.AccountSessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SignUpServlet extends HttpServlet {
    private final String invalidPage = "/views/signUp.jsp";

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
        String accountID = request.getParameter("accountID");
        String customerName = request.getParameter("customerName");
        String password = request.getParameter("password");
        String txtConfirm = request.getParameter("txtConfirm");
        String email = request.getParameter("email");
        AccountInsertError errorObj = new AccountInsertError();
        try {
            accountID = request.getParameter("accountID").trim();
            customerName = request.getParameter("customerName").trim();
            password = request.getParameter("password").trim();
            txtConfirm = request.getParameter("txtConfirm").trim();
            email = request.getParameter("email").trim();

        } catch (NullPointerException ex) {
            log("Someone send bad request: " + ex.getMessage());
            errorObj.setNullPointerErr("BAD REQUEST");
        } 


        if(accountID.length()<3 || accountID.length()>10) {
            errorObj.setUsernameLengthErr("Username must has 3 - 10 characters");

        }

        if(customerName.length()<3 || customerName.length()>50) {
            errorObj.setCustomerNameLengthErr("Full name must has 3 - 50 characters");

        }

        if(password.length()<6 || password.length()>20) {
            errorObj.setPasswordLengthErr("Password must has 6 - 20 characters");

        } else if (txtConfirm.equals(password) == false) {
            errorObj.setConfimNotMatchedErr("Confirm and password are not matched");
        }


        if(email.length()>50) {
            errorObj.setEmailLengthErr("Email cannot has more 50 characters.");
        }
        
        if (errorObj.isRaisedErrors()) {
            request.setAttribute("ERROROBJ", errorObj);
            RequestDispatcher rd = request.getRequestDispatcher(invalidPage);
            rd.forward(request, response);
        }
        
        try {
            Context context = new InitialContext();
            
            Object obj = context.lookup("AccountBeanLocalJNDI");
            
            AccountSessionBeanLocal poji = (AccountSessionBeanLocal) obj;
            
            boolean result = poji.createAccount(accountID, customerName, 
                    password, email);
            
            if (result == false) {
                errorObj.setUsernameEmailExisted("Username or Email has existed yet.");
                request.setAttribute("ERROROBJ", errorObj);
                RequestDispatcher rd = request.getRequestDispatcher(invalidPage);
                rd.forward(request, response);
                return;
            }
            
            String urlRewriting = "ProcessServlet";
            response.sendRedirect(urlRewriting);
            
            
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
