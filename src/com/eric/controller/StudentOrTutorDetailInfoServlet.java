package com.eric.controller;

import com.eric.dao.DeptDAO;
import com.eric.dao.MajorDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
import com.eric.entity.Student;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 管理员查看学生或导师的详细信息
@WebServlet(name = "StudentOrTutorDetailInfoServlet", urlPatterns = {"/studentDetailInfo.do", "/tutorDetailInfo.do"})
public class StudentOrTutorDetailInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        if ("student".equals(type)) {
            // 获得当前的学生实体
            String sno = request.getParameter("sno");
            Student student = StudentDAO.getInstance().getStudentBySno(sno);
            session.setAttribute("student", student);
            session.setAttribute("deptDAO", DeptDAO.getInstance());
            session.setAttribute("majorDAO", MajorDAO.getInstance());
            response.sendRedirect("admin_studentDetailInfo.jsp");
        }
        if ("tutor".equals(type)) {
            // 获得当前的导师实体
            String tno = request.getParameter("tno");
            Tutor tutor = TutorDAO.getInstance().getTutorByTno(tno);
            session.setAttribute("tutor", tutor);
            session.setAttribute("majorDAO", MajorDAO.getInstance());
            response.sendRedirect("admin_tutorDetailInfo.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
