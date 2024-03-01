/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

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
import model.Time_slot;
import util.DateHelper;
import java.sql.Date;

/**
 *
 * @author Nguyen Kim Duong
 */
public class TimeTableController extends HttpServlet {

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
            out.println("<title>Servlet TimeTableController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimeTableController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate lastDayOfWeek = currentDate.with(DayOfWeek.SUNDAY);
        request.setAttribute("fromDate", firstDayOfWeek);
        request.setAttribute("toDate", lastDayOfWeek);
        ArrayList<String> listDatesInWeek = DateHelper.getDatesInWeek(firstDayOfWeek, lastDayOfWeek);
        ArrayList<String> listNameDatesInWeek = DateHelper.getNameDatesInWeek(firstDayOfWeek, lastDayOfWeek);
        
        request.setAttribute("listDatesInWeek", listDatesInWeek);
        request.setAttribute("listNameDatesInWeek", listNameDatesInWeek);
        Time_slotDBContext timeDB = new Time_slotDBContext();
        ArrayList<Time_slot> times = timeDB.list();
        request.setAttribute("times", times);

        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Session> sessions = sessionDB.getSessionByDate(Date.valueOf(firstDayOfWeek), Date.valueOf(lastDayOfWeek), null);
        System.out.println(sessions.size());
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("../view/lecturer/time_table.jsp").forward(request, response);
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
        String raw_fromDate = request.getParameter("fromDate");
        String raw_toDate = request.getParameter("toDate");
        String lecturerId = request.getParameter("lid");
        if(lecturerId.isEmpty()) {
            lecturerId = null;
        }
        Date fromDate, toDate;
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate lastDayOfWeek = currentDate.with(DayOfWeek.SUNDAY);
        if (raw_fromDate == null) {
            fromDate = Date.valueOf(firstDayOfWeek);
        }else {
            fromDate = Date.valueOf(raw_fromDate);
        }
        if(raw_toDate == null) {
            toDate = Date.valueOf(lastDayOfWeek);
        }else {
            toDate = Date.valueOf(raw_toDate);
        }
        
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);
        
        ArrayList<String> listDatesInWeek = DateHelper.getDatesInWeek(fromDate.toLocalDate(), toDate.toLocalDate());
        ArrayList<String> listNameDatesInWeek = DateHelper.getNameDatesInWeek(fromDate.toLocalDate(), toDate.toLocalDate());
        
        request.setAttribute("listDatesInWeek", listDatesInWeek);
        request.setAttribute("listNameDatesInWeek", listNameDatesInWeek);
        Time_slotDBContext timeDB = new Time_slotDBContext();
        ArrayList<Time_slot> times = timeDB.list();
        request.setAttribute("times", times);

        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Session> sessions = sessionDB.getSessionByDate(fromDate, toDate, lecturerId);
        request.setAttribute("lid", lecturerId);
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("../view/lecturer/time_table.jsp").forward(request, response);
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
