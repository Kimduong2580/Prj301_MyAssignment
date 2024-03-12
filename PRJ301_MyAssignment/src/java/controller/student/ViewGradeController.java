/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.GradingSystemDBContext;
import dal.RegistrationDBContext;
import dal.SemesterDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import model.Account;
import model.GradingSystem;
import model.Registration;
import model.Semester;
import util.CalculatorAverageMark;

/**
 *
 * @author Nguyen Kim Duong
 */
public class ViewGradeController extends BaseRequiredAuthenticationController {

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
            String studentId = account.getStudent().getId();
            String semesterId = request.getParameter("seid");
            String subjectId = request.getParameter("subid");

            SemesterDBContext semesterDB = new SemesterDBContext();
            ArrayList<Semester> semesters = semesterDB.list();
            semesterId = (semesterId == null) ? semesters.get(semesters.size() - 1).getId() : semesterId;
            request.setAttribute("semesters", semesters);

            RegistrationDBContext registrationDB = new RegistrationDBContext();
            
            //lay ds cac registration ma student hk trong semester
            ArrayList<Registration> registrations = registrationDB.getRegistrationByStudentIdAndSemesterId(studentId, semesterId);
            if (registrations.size() > 0) {
                subjectId = (subjectId == null) ? registrations.get(0).getSubject().getId() : subjectId;
                request.setAttribute("registrations", registrations);

                GradingSystemDBContext gsDB = new GradingSystemDBContext();
                ArrayList<GradingSystem> gss = gsDB.getGradingSystemsBysidAndSubidAndSeId(studentId, semesterId, subjectId);
                request.setAttribute("gss", gss);

                HashMap<String, Integer> categoryAss = new HashMap<>();
                for (GradingSystem gs : gss) {
                    String category = gs.getAssessment().getCategory();
                    if (categoryAss.containsKey(category)) {
                        categoryAss.put(category, categoryAss.get(category) + 1);
                    } else {
                        categoryAss.put(category, 1);
                    }
                }
                request.setAttribute("categoryAss", categoryAss);

                Registration registrationItem = registrationDB.getT(studentId, semesterId, subjectId);
                System.out.println(registrationItem.getAverageMark());
                request.setAttribute("registrationItem", registrationItem);
            }
            request.getRequestDispatcher("../view/student/view_grade.jsp").forward(request, response);

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
