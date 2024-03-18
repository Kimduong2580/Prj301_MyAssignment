/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication.authorization;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Account;
import model.Role;

/**
 *
 * @author Nguyen Kim Duong
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    public ArrayList<Role> list(HttpServletRequest req, Account account) {
        RoleDBContext roleDB = new RoleDBContext();
        String url = req.getServletPath();
        ArrayList<Role> roles = roleDB.list(account.getId(), url);
        return roles;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = list(req, account);

        if (roles.size() >= 1) {
            doPost(req, resp, account, roles);
        } else {
            PrintWriter out = resp.getWriter();
            out.print("Access denied!!");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = list(req, account);

        if (roles.size() >= 1) {
            doGet(req, resp, account, roles);
        } else {
            PrintWriter out = resp.getWriter();
            out.print("Access denied!!");
        }
    }

}
