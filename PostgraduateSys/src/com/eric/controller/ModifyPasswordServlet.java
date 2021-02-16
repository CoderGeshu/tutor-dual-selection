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

@WebServlet(name = "ModifyPasswordServlet",urlPatterns = {"/modifyPassword.do"})
public class ModifyPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        // 获得账号及类型
        String account = (String) session.getAttribute("account");
        String type = (String) session.getAttribute("type");
        String newPassword = request.getParameter("newPassword");
        boolean flag = false;
        switch (type) {
            case "student":
                flag = StudentDAO.getInstance().modifyPassword(account, newPassword);
                session.setAttribute("student",StudentDAO.getInstance().getStudentBySno(account));
                break;
            case "tutor":
                flag = TutorDAO.getInstance().modifyPassword(account, newPassword);
                session.setAttribute("tutor",TutorDAO.getInstance().getTutorByTno(account));
                break;
            case "admin":
                flag = AdminDAO.getInstance().modifyPassword(account, newPassword);
                session.setAttribute("admin",AdminDAO.getInstance().getAdminByAno(account));
                break;
        }
        if (flag) {
            out.println("<script>alert('修改成功!'); window.location='welcome.jsp';</script>");
        } else {
            out.println("<script>alert('修改失败!'); window.location='modifyPassword.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
