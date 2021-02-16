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

@WebServlet(urlPatterns = {"/supAdminStudentTable.do", "/supAdminTutorTable.do"})
public class SupAdminStuAndTuTableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获得超管要查询的类型：学生或导师
        String type = request.getParameter("type");
        // 获取当前管理员要查询的系别号
        String dno = request.getParameter("dno");
        if ("student".equals(type)) { // 如果查询学生
            List<Student> students = new ArrayList<>();
            if ("all".equals(dno)) {   // 获得全部学生
                students = StudentDAO.getInstance().getAllStudent();
            } else {    // 否则根据系别号获得学生
                // 根据系别号获得所有专业
                List<Major> majors = MajorDAO.getInstance().getMajorsByDno(dno);
                // 获得每个专业下的学生
                for (Major major : majors) {
                    List<Student> tmpList = StudentDAO.getInstance().getStudentsByMno(major.getMno());
                    students.addAll(tmpList);
                }
            }
            session.setAttribute("students", students);
            session.setAttribute("majorDAO", MajorDAO.getInstance());
            session.setAttribute("tutorDAO", TutorDAO.getInstance());
            response.sendRedirect("supAdmin_studentInfoTable.jsp");
        }
        if ("tutor".equals(type)) {   // 如果查询导师
            List<Tutor> tutors = new ArrayList<>();
            if ("all".equals(dno)) {  // 获得全部导师
                tutors = TutorDAO.getInstance().getAllTutor();
            } else {     // 否则根据系别号获得导师
                // 根据系别号获得所有专业
                List<Major> majors = MajorDAO.getInstance().getMajorsByDno(dno);
                // 获得每个专业下的导师
                for (Major major : majors) {
                    List<Tutor> tmpList = TutorDAO.getInstance().getTutorsByMno(major.getMno());
                    tutors.addAll(tmpList);
                }
            }
            session.setAttribute("tutors", tutors);
            session.setAttribute("majorDAO", MajorDAO.getInstance());
            session.setAttribute("tutorDAO", TutorDAO.getInstance());
            response.sendRedirect("supAdmin_tutorInfoTable.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
