package com.eric.controller;

import com.eric.dao.*;
import com.eric.entity.Dept;
import com.eric.entity.Major;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TutorLoginServlet",urlPatterns = {"/tutor.do"})
public class TutorLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        String type = (String) session.getAttribute("type");
        //  把导师、专业、系别实体存储到会话
        Tutor tutor = TutorDAO.getInstance().getTutorByTno(account);
        session.setAttribute("tutor", tutor);
        Major major = MajorDAO.getInstance().getMajorByMno(tutor.getMno());
        session.setAttribute("major", major);
        Dept dept = DeptDAO.getInstance().getDeptByDno(major.getDno());
        session.setAttribute("dept", dept);
        // 添加日志信息
        LogInfoDAO.getInstance().addLogInfo(account,tutor.getTname());
        // 定向到导师功能页面
        response.sendRedirect("tutor.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
