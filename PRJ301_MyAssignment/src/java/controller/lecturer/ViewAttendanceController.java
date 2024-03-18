/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.authentication.authorization.BaseRBACController;
import dal.AttendanceDBContext;
import dal.StudentDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Role;
import model.Session;
import model.Student;

/**
 *
 * @author Nguyen Kim Duong
 */
public class ViewAttendanceController extends BaseRBACController {

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
            out.println("<title>Servlet ViewAttendanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAttendanceController at " + request.getContextPath() + "</h1>");
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
     protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
            throws ServletException, IOException {
        String sessionId = request.getParameter("seid");
        System.out.println(sessionId);
        SessionDBContext sessionDB = new SessionDBContext();
        Session session = sessionDB.getSesionBysesId(sessionId);
        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<Student> students = studentDB.listStudentBygId(session.getGroup().getId());
//        System.out.println("Student:" +students.size());
        request.setAttribute("gname", session.getGroup().getName());
        request.setAttribute("students", students);
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        ArrayList<Attendance> attedances = attendanceDB.getAttendancesBySessionId(sessionId);
//        System.out.println(attedances.size());
        request.setAttribute("attendances", attedances);
        
        request.getRequestDispatcher("../view/lecturer/view_attendance.jsp").forward(request, response);
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
     protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
            throws ServletException, IOException {
        
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
