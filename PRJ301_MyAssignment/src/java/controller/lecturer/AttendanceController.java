/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.AttendanceDBContext;
import dal.StudentDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;

/**
 *
 * @author Nguyen Kim Duong
 */
public class AttendanceController extends HttpServlet {

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
            out.println("<title>Servlet AttendanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceController at " + request.getContextPath() + "</h1>");
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
        String sessionId = request.getParameter("sid");
        SessionDBContext sessionDB = new SessionDBContext();
        Session session = sessionDB.getSesionBysesId(sessionId);
        // Lấy ngày hiện tại
        Calendar currentDate = Calendar.getInstance();
        // Lấy ngày của phiên
        Calendar sessionDate = Calendar.getInstance();
        sessionDate.setTime(session.getDate());

        //Compare current date with session date, if more than 2 return time-table 
        if (currentDate.after(sessionDate)) {
            request.getRequestDispatcher("../view/lecturer/expireAttendance.jsp").forward(request, response);
        } else {
        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<Student> students = studentDB.listStudentBygId(session.getGroup().getId());
        request.setAttribute("gname", session.getGroup().getName());
        request.setAttribute("students", students);

        request.getRequestDispatcher("../view/lecturer/attendance.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionId = request.getParameter("sid");
        SessionDBContext sessionDB = new SessionDBContext();
        Session session = sessionDB.getSesionBysesId(sessionId);

        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<Student> students = studentDB.listStudentBygId(session.getGroup().getId());

        //insert recored attendance
        for (Student student : students) {
            String raw_status = request.getParameter("attendance-" + student.getId());
            Boolean status = raw_status == null ? null : Boolean.parseBoolean(raw_status);
            String comment = request.getParameter("description-" + student.getId());
            AttendanceDBContext attendanceDB = new AttendanceDBContext();
            attendanceDB.insert(sessionId, student.getId(), status, comment);
        }
        //Update isTaken
        sessionDB.updateIsTaken(sessionId);
        request.getRequestDispatcher("time_table").forward(request, response);
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
