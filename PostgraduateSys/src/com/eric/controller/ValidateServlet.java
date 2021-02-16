package com.eric.controller;

import com.eric.dao.AdminDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ValidateServlet",urlPatterns = {"/validate.do"})
public class ValidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        // 获得传递的账号和密码
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        // 存储到session
        session.setAttribute("account", account);
        session.setAttribute("password", password);
        session.setAttribute("type", type);
        switch (type) {
            case "student":
                if (StudentDAO.getInstance().validate(account, password)) {
//                    out.print("<script>alert('学生登录成功')</script>");
                    response.sendRedirect("login_success.jsp");
                }
                break;
            case "tutor":
                if (TutorDAO.getInstance().validate(account, password)) {
                    response.sendRedirect("login_success.jsp");
                }
                break;
            case "supAdmin":
                if (AdminDAO.getInstance().validate(account, password, 0)) {
                    response.sendRedirect("login_success.jsp");
                }
                break;
            case "admin":
                if (AdminDAO.getInstance().validate(account, password, 1)) {
                    response.sendRedirect("login_success.jsp");
                }
                break;
        }
        // 如果上述都不成功，则输入有误，登录失败
        out.print("<script>alert('用户信息有误，登录失败!'); window.history.back();</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
