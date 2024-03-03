/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.student;

import dal.SessionDBContext;
import dal.Time_slotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Session;
import util.DateHelper;
import java.sql.Date;
import model.Time_slot;

/**
 *
 * @author Nguyen Kim Duong
 */
public class TimeTableController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet TimeTableController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimeTableController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String studentId = request.getParameter("sid");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayInWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate lastDayInWeek = currentDate.with(DayOfWeek.SUNDAY);
        request.setAttribute("fromDate", firstDayInWeek);
        request.setAttribute("toDate", lastDayInWeek);
        
        ArrayList<String> listDatesInWeek = DateHelper.getDatesInWeek(firstDayInWeek, lastDayInWeek);
        ArrayList<String> listNameDatesInWeek = DateHelper.getNameDatesInWeek(firstDayInWeek, lastDayInWeek);
        request.setAttribute("listDatesInWeek", listDatesInWeek);
        request.setAttribute("listNameDatesInWeek", listNameDatesInWeek);
        
        Time_slotDBContext timeDB = new Time_slotDBContext();
        ArrayList<Time_slot> times = timeDB.list();
        request.setAttribute("times", times);
        
        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Session> sessions = sessionDB.getSessionByDateAndStudentId(Date.valueOf(firstDayInWeek), Date.valueOf(lastDayInWeek), "HE171819");
        request.setAttribute("sessions", sessions);
        
        request.getRequestDispatcher("../view/student/time_table.jsp").forward(request, response);
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
