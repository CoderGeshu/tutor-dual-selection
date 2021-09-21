package com.eric.controller;

import com.eric.dao.*;
import com.eric.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/admin.do", "/supAdmin.do"})
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ano = (String) session.getAttribute("account");
        String type = (String) session.getAttribute("type");
        //  获得管理员实体，并存储到会话
        Admin admin = AdminDAO.getInstance().getAdminByAno(ano);
        session.setAttribute("admin", admin);
        if ("admin".equals(type)) {  // 如果是普通管理员
            session.setAttribute("typeName", "系管理员");
            // 把管理员、管理的系别、系别下的专业实体列表存储到会话
            Dept dept = DeptDAO.getInstance().getDeptByAno(admin.getAno());
            session.setAttribute("dept", dept);
            List<Major> majors = MajorDAO.getInstance().getMajorsByDno(dept.getDno());
            session.setAttribute("majors", majors);
            // 添加登录日志信息
            LogInfoDAO.getInstance().addLogInfo(ano,admin.getAname());
            // 定向到管理员页面
            response.sendRedirect("admin.jsp");
        } else {   // 超级管理员
            session.setAttribute("typeName", "研究生院管理员");
            // 负责所有系别
            List<Dept> depts = DeptDAO.getInstance().getAllDepts();
            session.setAttribute("depts", depts);
            // 为显示个人信息设置的一个独特的系别，只有系别名且名字为所有系别
            Dept dept = new Dept();
            dept.setDname("所有系别");
            session.setAttribute("dept", dept);
            // 添加日志信息
            LogInfoDAO.getInstance().addLogInfo(ano,admin.getAname());
            // 定向到管理员页面
            response.sendRedirect("supAdmin.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
