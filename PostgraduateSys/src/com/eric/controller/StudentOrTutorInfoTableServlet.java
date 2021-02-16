package com.eric.controller;

import com.eric.dao.MajorDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/studentInfoTable.do","/tutorInfoTable.do"})
public class StudentOrTutorInfoTableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取当前管理员要查询的类别：学生或老师
        String type = request.getParameter("type");
        // 获取当前管理员要查询的专业号
        String mno = request.getParameter("mno");

        if ("student".equals(type)) { // 如果查询学生
            List<Student> students = new ArrayList<>();
            if ("all".equals(mno)) {  // 查询当前学院所有专业的学生
                List<Major> majors = (List<Major>) session.getAttribute("majors");
                for (Major major : majors) {
                    List<Student> tmpList = StudentDAO.getInstance().getStudentsByMno(major.getMno());
                    students.addAll(tmpList);
                }
            } else {   // 否则就是根据传过来的专业号查找学生
                students = StudentDAO.getInstance().getStudentsByMno(mno);
            }
            // 把查询结果列表存储到会话
            session.setAttribute("students", students);
            session.setAttribute("majorDAO", MajorDAO.getInstance());
            session.setAttribute("tutorDAO", TutorDAO.getInstance());
            // 重定向
            response.sendRedirect("admin_studentInfoTable.jsp");
        }
        if ("tutor".equals(type)) {  // 如果查询导师
            List<Tutor> tutors = new ArrayList<>();
            if ("all".equals(mno)) {  // 查询当前学院所有专业的学生
                List<Major> majors = (List<Major>) session.getAttribute("majors");
                for (Major major : majors) {
                    List<Tutor> tmpList = TutorDAO.getInstance().getTutorsByMno(major.getMno());
                    tutors.addAll(tmpList);
                }
            } else {   // 否则就是根据传过来的专业号查找学生
                tutors = TutorDAO.getInstance().getTutorsByMno(mno);
            }
            // 把查询结果列表存储到会话
            session.setAttribute("tutors", tutors);
            session.setAttribute("majorDAO", MajorDAO.getInstance());
            session.setAttribute("tutorDAO", TutorDAO.getInstance());
            // 重定向
            response.sendRedirect("admin_tutorInfoTable.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
