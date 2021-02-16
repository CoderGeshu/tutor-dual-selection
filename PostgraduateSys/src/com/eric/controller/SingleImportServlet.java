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
import java.io.PrintWriter;
import java.util.List;

// 单个导入信息
@WebServlet(name = "SingleImportServlet", urlPatterns = {"/singleImportStudent.do", "/singleImportTutor.do"})
public class SingleImportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        // 获得操作类型
        String op = request.getParameter("op");
        // 获得将要导入的用户类型
        String type = request.getParameter("type");
        if (op == null) {    // 如果无操作
            // 获得所有专业，并存储到session，重定向到单个录入界面
            List<Major> majors = MajorDAO.getInstance().getAllMajors();
            session.setAttribute("majors", majors);
            if ("student".equals(type)) {
                response.sendRedirect("supAdmin_singleImportStudent.jsp");
            }
            if ("tutor".equals(type)) {
                response.sendRedirect("supAdmin_singleImportTutor.jsp");
            }
        }
        if ("add".equals(op)) {  // 如果是确定录入信息
            if ("student".equals(type)) {  // 如果录入的为学生
                // 获得录入的学生实体信息
                Student student = new Student();
                student.setSno(request.getParameter("sno"));
                student.setPassword(request.getParameter("sno"));
                student.setSname(request.getParameter("sname"));
                student.setSex(request.getParameter("sex"));
                student.setBirth(request.getParameter("birth"));
                student.setGpa(Float.parseFloat(request.getParameter("gpa")));
                student.setTel(request.getParameter("tel"));
                student.setEmail(request.getParameter("email"));
                student.setMno(request.getParameter("mno"));
                if (StudentDAO.getInstance().addStudent(student)) {
                    out.println("<h2>录入成功，正在跳转...</h2>");
                }
                response.setHeader("refresh", "2;url=singleImportStudent.do?type=student");
            }
            if ("tutor".equals(type)) {    // 如果录入的是导师
                // 获得录入的导师信息
                Tutor tutor = new Tutor();
                tutor.setTno(request.getParameter("tno"));
                tutor.setPassword(request.getParameter("tno"));
                tutor.setTname(request.getParameter("tname"));
                tutor.setSex(request.getParameter("sex"));
                tutor.setBirth(request.getParameter("birth"));
                tutor.setTitle(request.getParameter("title"));
                tutor.setTel(request.getParameter("tel"));
                tutor.setEmail(request.getParameter("email"));
                tutor.setAbility(Integer.parseInt(request.getParameter("ability")));
                tutor.setMno(request.getParameter("mno"));
                if (TutorDAO.getInstance().addTutor(tutor)) {
                    out.println("<h2>录入成功，正在跳转...</h2>");
                }
                response.setHeader("refresh","2;url=singleImportTutor.do?type=tutor");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
