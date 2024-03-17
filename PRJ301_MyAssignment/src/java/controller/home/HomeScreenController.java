/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Session;

/**
 *
 * @author Nguyen Kim Duong
 */
public class HomeScreenController extends BaseRequiredAuthenticationController {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainScreenController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainScreenController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        if (account == null) {
            request.getRequestDispatcher("view/authentication/login/loginNotification.jsp").forward(request, response);
        } else {
            if (account.getLecturer() != null) {
                SessionDBContext sessionDB = new SessionDBContext();
                LocalDate currentDate = LocalDate.now();
                LocalDate yesterday = currentDate.minusDays(1);
                ArrayList<Session> sessions = sessionDB.getSessionByDateAndLecturerId(Date.valueOf(yesterday), Date.valueOf(currentDate), account.getLecturer().getId());
                request.setAttribute("sessions", sessions);
                request.setAttribute("account", account);
                request.getRequestDispatcher("view/home_screen/lecturer/screen.jsp").forward(request, response);
            } else {
                request.setAttribute("account", account);
                request.getRequestDispatcher("view/home_screen/student/screen.jsp").forward(request, response);
            }
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

        if (account == null) {
            request.getRequestDispatcher("view/authentication/login/loginNotification.jsp").forward(request, response);
        } else {
            if (account.getLecturer() != null) {
                SessionDBContext sessionDB = new SessionDBContext();
                LocalDate currentDate = LocalDate.now();
                LocalDate yesterday = currentDate.minusDays(1);
                ArrayList<Session> sessions = sessionDB.getSessionByDateAndLecturerId(Date.valueOf(yesterday), Date.valueOf(currentDate), account.getLecturer().getId());
                request.setAttribute("sessions", sessions);
                request.setAttribute("account", account);
                request.getRequestDispatcher("view/home_screen/lecturer/screen.jsp").forward(request, response);
            } else {
                request.setAttribute("account", account);
                request.getRequestDispatcher("view/main_screen/student/screen.jsp").forward(request, response);
            }
        }
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