package com.eric.controller;

import com.eric.dao.*;
import com.eric.entity.Dept;
import com.eric.entity.Major;
import com.eric.entity.Student;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentServlet",urlPatterns = {"/student.do"})
public class StudentLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sno = (String) session.getAttribute("account");
        String type = (String) session.getAttribute("type");
        //  把学生、专业、系别、导师实体存储到会话
        Student student = StudentDAO.getInstance().getStudentBySno(sno);
        session.setAttribute("student", student);
        Major major = MajorDAO.getInstance().getMajorByMno(student.getMno());
        session.setAttribute("major", major);
        Dept dept = DeptDAO.getInstance().getDeptByDno(major.getDno());
        session.setAttribute("dept", dept);
        if (student.getTno() != null) {
            Tutor tutor = TutorDAO.getInstance().getTutorByTno(student.getTno());
            session.setAttribute("tutor", tutor);
        }
        // 添加日志信息
        LogInfoDAO.getInstance().addLogInfo(sno,student.getSname());
        // 定向到学生页面
        response.sendRedirect("student.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
