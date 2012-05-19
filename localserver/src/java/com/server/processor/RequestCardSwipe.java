/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.processor;

import com.server.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuuga
 */
public class RequestCardSwipe extends HttpServlet {

    private String cardId;
    private User user;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            cardId = request.getParameter("CARDID");

            System.out.println("========================================");
            System.out.println(": : : CLIENT REQUEST FOR CHECKING CARD:.");
            System.out.println("========================================");
            System.out.println(": : : CARDID                [" + cardId + "]");
            System.out.println("=======================================");

            if (cardId == null || cardId.trim().equalsIgnoreCase("")) {
                System.out.println(": : : PARAMETER NULL:.");
                result="03|NULL REQUEST";
            } else {
                user=new User();
                result=user.swipeCardSql(cardId);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(": : : ERROR ON PROCESSION RequestCardSwipe:.");
        } finally {
            System.out.println(": : : RESULT ["+result+"]");
            out.println(result);
            session.invalidate();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
