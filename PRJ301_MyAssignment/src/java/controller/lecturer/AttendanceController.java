/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.EnrollmentDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;

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
        Session session = sessionDB.getT(sessionId);
        EnrollmentDBContext enrollmentDB = new EnrollmentDBContext();
        ArrayList<Student> students = enrollmentDB.listStudentBygId(session.getGroup().getId());
        request.setAttribute("gname", session.getGroup().getName());
        request.setAttribute("students", students);

        request.getRequestDispatcher("../view/lecturer/attendance.jsp").forward(request, response);
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
        sessionId = "s31";
        SessionDBContext sessionDB = new SessionDBContext();
        Session session = sessionDB.getT(sessionId);
        EnrollmentDBContext enrollmentDB = new EnrollmentDBContext();
        ArrayList<Student> students = enrollmentDB.listStudentBygId(session.getGroup().getId());
        ArrayList<Attendance> attendances = new ArrayList<>();
        for (Student student : students) {
            String raw_status = request.getParameter("attendance-" + student.getId());
            Boolean status = raw_status==null?false:Boolean.parseBoolean(raw_status);
            String comment = request.getParameter("comment-" + student.getId());
            Attendance att = new Attendance();
            att.setSession(session);
            att.setStudent(student);
            att.setIsPresent(status);
            att.setDescription(comment);
//            att.setDatetime(datetime);
            attendances.add(att);
        }
        System.out.println(attendances.size());
        for (Attendance attendance : attendances) {
            System.out.println(attendance.getStudent().getId());
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
