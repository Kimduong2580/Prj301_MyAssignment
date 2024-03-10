/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.AttendanceRecordDBContext;
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
import model.Account;
import model.AttendanceRecord;
import model.Time_slot;

/**
 *
 * @author Nguyen Kim Duong
 */
public class TimeTableController extends BaseRequiredAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (account.getStudent() == null) {
            out.print("access denied");
        } else {
            String displayName = account.getDisplayName();
            request.setAttribute("displayName", displayName);
            String studentId;
            studentId = account.getStudent().getId();
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

            AttendanceRecordDBContext attendanceRecordDB = new AttendanceRecordDBContext();
            ArrayList<AttendanceRecord> attendanceRecords = attendanceRecordDB.getAttendanceRecordsBysIdAndFromDateAndToDate(studentId, Date.valueOf(firstDayInWeek), Date.valueOf(lastDayInWeek));
            System.out.println(attendanceRecords.size());
            request.setAttribute("attendanceRecords", attendanceRecords);

            request.getRequestDispatcher("../view/student/time_table.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        if (account.getStudent() == null) {
            out.print("access denied");
        } else {
            String studentId = account.getStudent().getId();
            String raw_fromDate = request.getParameter("fromDate");
            String raw_toDate = request.getParameter("toDate");
            LocalDate currentDate = LocalDate.now();
            LocalDate firstDayInWeek = currentDate.with(DayOfWeek.MONDAY);
            LocalDate lastDayInWeek = currentDate.with(DayOfWeek.SUNDAY);
            Date fromDate = raw_fromDate == null ? Date.valueOf(firstDayInWeek) : Date.valueOf(raw_fromDate);

            Date toDate = raw_toDate == null ? Date.valueOf(lastDayInWeek) : Date.valueOf(raw_toDate);
            request.setAttribute("fromDate", fromDate);
            request.setAttribute("toDate", toDate);
            ArrayList<String> listDatesInWeek = DateHelper.getDatesInWeek(fromDate.toLocalDate(), toDate.toLocalDate());
            ArrayList<String> listNameDatesInWeek = DateHelper.getNameDatesInWeek(fromDate.toLocalDate(), toDate.toLocalDate());
            request.setAttribute("listDatesInWeek", listDatesInWeek);
            request.setAttribute("listNameDatesInWeek", listNameDatesInWeek);

            Time_slotDBContext timeDB = new Time_slotDBContext();
            ArrayList<Time_slot> times = timeDB.list();
            request.setAttribute("times", times);

            AttendanceRecordDBContext attendanceRecordDB = new AttendanceRecordDBContext();
            ArrayList<AttendanceRecord> attendanceRecords = attendanceRecordDB.getAttendanceRecordsBysIdAndFromDateAndToDate(studentId, fromDate, toDate);
            request.setAttribute("attendanceRecords", attendanceRecords);

            request.getRequestDispatcher("../view/student/time_table.jsp").forward(request, response);
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
