package com.eric.controller;

import com.eric.dao.TutorDAO;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentMajorTutorServlet", urlPatterns = {"/studentMajorTutor.do"})
public class StudentMajorTutorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取专业号，并利用专业号获取导师列表
        String mno = request.getParameter("mno");
        List<Tutor> tutors = TutorDAO.getInstance().getTutorsByMno(mno);
        // 设置session存储
        session.setAttribute("tutors",tutors);
        session.setAttribute("tutorDAO",TutorDAO.getInstance());
        // 跳转到学生导师信息表页面
        response.sendRedirect("student_majorTutorTable.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
