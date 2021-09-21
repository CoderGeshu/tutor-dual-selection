package com.eric.controller;

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

@WebServlet(name = "UserModifyPersonalInfoServlet", urlPatterns = {"/userModifyPersonalInfo.do"})
public class UserModifyPersonalInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // 获得修改信息的参数
        String type = request.getParameter("type");
        String account = request.getParameter("account");
        String birth = request.getParameter("birth");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String description = request.getParameter("description");

        // 如果是学生更新信息
        if ("student".equals(type)) {
            if (StudentDAO.getInstance().updateInfoByStudent(account, birth, tel, email, description)) {
                out.println("<h3>修改成功，正在跳转...</h3>");
            }
            // 更新后学生信息重新添加到session
            session.setAttribute("student", StudentDAO.getInstance().getStudentBySno(account));
            // 2秒后返回当前学生页面信息
            response.setHeader("refresh", "2;url=student_personalInfo.jsp");
        }
        // 如果是导师更新信息
        if ("tutor".equals(type)){
            if (TutorDAO.getInstance().updateInfoByTutor(account, birth, tel, email, description)) {
                out.println("<h3>修改成功，正在跳转...</h3>");
            }
            // 更新后导师信息重新添加到session
            session.setAttribute("tutor", TutorDAO.getInstance().getTutorByTno(account));
            // 2秒后返回当前导师页面信息
            response.setHeader("refresh", "2;url=tutor_personalInfo.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
