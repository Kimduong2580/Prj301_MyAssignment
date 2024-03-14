/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.GroupDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Group;
import model.Student;

/**
 *
 * @author Nguyen Kim Duong
 */
public class ListStudentController extends BaseRequiredAuthenticationController {

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        if (account.getLecturer().getId() != null) {
            String lid = account.getLecturer().getId();
            GroupDBContext groupDB = new GroupDBContext();
            ArrayList<Group> groups = groupDB.list(lid);
            if (groups.size() > 0) {
                request.setAttribute("groups", groups);
            } else {
                request.setAttribute("emptyGroups", "The lecturer does not teach any class");
            }
//            System.out.println(account.getLecturer().getName());
            request.setAttribute("lname", account.getDisplayName());
            request.getRequestDispatcher("../view/lecturer/list_student.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        if (account.getLecturer().getId() != null) {
            String lid = account.getLecturer().getId();
            GroupDBContext groupDB = new GroupDBContext();
            ArrayList<Group> groups = groupDB.list(lid);
            String gid = request.getParameter("gid");
            StudentDBContext studentDB = new StudentDBContext();
            ArrayList<Student> students = studentDB.listStudentBygId(gid);
            String gname = "";
            for (Group g : groups) {
                if(g.getId().equals(gid)) {
                    gname = g.getName();
                }
            }
            request.setAttribute("gname", gname);
            request.setAttribute("groups", groups);
            request.setAttribute("students", students);
            request.setAttribute("lname", account.getDisplayName());
            request.getRequestDispatcher("../view/lecturer/list_student.jsp").forward(request, response);
        }
    }

}
