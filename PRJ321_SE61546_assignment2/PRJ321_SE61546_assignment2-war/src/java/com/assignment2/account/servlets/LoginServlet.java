/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.account.servlets;

import com.assignment2.account.AccountLoginError;
import com.assignment2.sessionBeans.AccountSessionBeanLocal;
import java.io.IOException;
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
public class LoginServlet extends HttpServlet {
    private final String searchPage = "/views/search.jsp";
    private final String invalidPage = "/views/login.jsp";
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
        
        String loginUser = request.getParameter("accountID");
        String loginPass = request.getParameter("password");

        AccountLoginError errorObj = new AccountLoginError();

        try {
            if (loginUser.equals("")) {
                errorObj.setNullUsernameErr("Username Field cannot be NULL");
            }

            if (loginUser.equals("")) {
                errorObj.setNullPasswordErr("Password Field cannot be NULL");
            }
        } catch (NullPointerException ex) {
            log("Bad request! Some one try to send bad request to system.");
            errorObj.setNullPointerErr("Cannot login! There's something wrong!");
        }
        
        if (errorObj.isRaisedErrors()) {
            request.setAttribute("ERROROBJ", errorObj);
            RequestDispatcher rd = request.getRequestDispatcher(invalidPage);
            rd.forward(request, response);
            return;
        }


        try {
            Context context = new InitialContext();
            Object obj = context.lookup("AccountBeanLocalJNDI");

            AccountSessionBeanLocal poji = (AccountSessionBeanLocal) obj;

            boolean result = poji.checkLogin(loginUser, loginPass);

            String url = searchPage;
            if (result == false) {
                errorObj.setInvalidUsernamePasswordErr("Invalid username or"
                        + " password. Please, try again!");
                request.setAttribute("ERROROBJ", errorObj);
                url = invalidPage;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("LOGINUSER", loginUser);
                session.setAttribute("LOGINPASS", loginPass);
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
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
