/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.common;

import com.assignment2.sessionBeans.AccountSessionBeanLocal;
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
public class NullServlet extends HttpServlet {
    private final String loginPage = "/views/login.html";
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(false);
            
            if (session == null) {
                request.getRequestDispatcher(loginPage)
                        .forward(request, response);
                return;
            }
            
            String loginUser = (String) session.getAttribute("LOGINUSER");
            String loginPass = (String) session.getAttribute("LOGINPASS");
            
            try {
                Context context = new InitialContext();
                Object obj = context.lookup("AccountBeanLocalJNDI");
                
                AccountSessionBeanLocal poji = (AccountSessionBeanLocal) obj;
                
                boolean result = poji.checkLogin(loginUser, loginPass);
                
                String url = searchPage;
                if (result == false) {
                    url = loginPage;
                }
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                
                
            } catch (NamingException ex) {
                log(ex.getMessage());
                response.sendError(500);
            }
            
            
        } finally {
            out.close();
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
