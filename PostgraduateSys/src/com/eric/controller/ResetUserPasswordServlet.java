package com.eric.controller;

import com.eric.dao.AdminDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResetUserPasswordServlet", urlPatterns = {"/resetPassword.do"})
public class ResetUserPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // 获得重置密码的类型
        String type = request.getParameter("type");
        // 获得重置的账号
        String resetAccount = request.getParameter("resetAccount");
        switch (type) {
            case "student":
                if (StudentDAO.getInstance().modifyPassword(resetAccount, resetAccount)){
                    out.println("<script>alert('重置成功');window.history.back();</script>");
                } else {
                    out.println("<script>alert('输入有误');window.history.back();</script>");
                }
                break;
            case "tutor":
                if (TutorDAO.getInstance().modifyPassword(resetAccount, resetAccount)){
                    out.println("<script>alert('重置成功');window.history.back();</script>");
                } else {
                    out.println("<script>alert('输入有误');window.history.back();</script>");
                }
                break;
            case "admin":
                if (AdminDAO.getInstance().modifyPassword(resetAccount, resetAccount)){
                    out.println("<script>alert('重置成功');window.history.back();</script>");
                } else {
                    out.println("<script>alert('输入有误');window.history.back();</script>");
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
