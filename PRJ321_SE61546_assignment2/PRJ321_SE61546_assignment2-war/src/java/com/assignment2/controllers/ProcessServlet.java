/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hau
 */
public class ProcessServlet extends HttpServlet {
    private final String nullServlet = "actions/NullServlet";
    private final String loginServlet = "actions/LoginServlet";
    private final String searchServlet = "actions/SearchServlet";
    private final String signUpPage = "views/signUp.html";
    private final String signUpServlet = "actions/SignUpServlet";
    private final String viewDetailServlet = "actions/ViewDetailServlet";
    private final String deleteDetailServlet = "actions/DeleteDetailServlet";
    private final String searchPage = "views/search.jsp";
    private final String loginPage = "views/login.html";
    private final String updateDetailServlet = "actions/UpdateDetailServlet";

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
            String button = request.getParameter("btAction");
            
            if (button == null) {
                request.getRequestDispatcher(nullServlet)
                        .forward(request, response);
                return;
            }
            
            if (button.equals("Login")) {
                RequestDispatcher rd = request.getRequestDispatcher(loginServlet);
                rd.forward(request, response);
            } else if (button.equals("Search")) {
                RequestDispatcher rd = request.getRequestDispatcher(searchServlet);
                rd.forward(request, response);
            } else if (button.equals("signUp")) {
                RequestDispatcher rd = request.getRequestDispatcher(signUpPage);
                rd.forward(request, response);
            }  else if (button.equals("Sign Up!")) {
                RequestDispatcher rd = request.getRequestDispatcher(signUpServlet);
                rd.forward(request, response);
            } else if (button.equals("view_detail")) {
                RequestDispatcher rd = request.getRequestDispatcher(viewDetailServlet);
                rd.forward(request, response);
            }  else if (button.equals("del_detail")) {
                RequestDispatcher rd = request.getRequestDispatcher(deleteDetailServlet);
                rd.forward(request, response);
            }  else if (button.equals("searchPage")) {
                RequestDispatcher rd = request.getRequestDispatcher(searchPage);
                rd.forward(request, response);
            } else if (button.equals("loginPage")) {
                RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                rd.forward(request, response);
            } else if (button.equals("Update")) {
                RequestDispatcher rd = request.getRequestDispatcher(updateDetailServlet);
                rd.forward(request, response);
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
