/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;

/**
 *
 * @author Nguyen Kim Duong
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    public Account getAuthenticatedByAccount(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies.length != 0) {
                String username = null;
                String password = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {
                        username = cooky.getValue();
                    }
                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }
                    if (username != null && password != null) {
                        break;
                    }
                }
                AccountDBContext accountDB = new AccountDBContext();
                account = accountDB.getT(username, password);
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
            }
        }
        return account;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Account account = getAuthenticatedByAccount(req);
        if (account == null) {
            out.print("access denied");
        } else {
            doPost(req, resp, account);
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Account account = getAuthenticatedByAccount(req);
        if (account == null) {
            out.print("access denied");
        } else {
            doGet(req, resp, account);
        }
    }

}
