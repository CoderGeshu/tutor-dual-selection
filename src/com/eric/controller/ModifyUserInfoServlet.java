package com.eric.controller;

import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ModifyUserInfoServlet", urlPatterns = {"/modifyStudentInfo.do", "/modifyTutorInfo.do"})
public class ModifyUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        if ("student".equals(type)) {  // 如果是管理员修改学生的信息
            String sno = request.getParameter("sno");
            String sname = request.getParameter("sname");
            String sex = request.getParameter("sex");
            String birth = request.getParameter("birth");
            String gpa = request.getParameter("gpa");
            if (StudentDAO.getInstance().updateInfoByAdmin(sno, sname, sex, birth, gpa)) {
                out.println("<h3>修改成功，正在跳转...</h3>");
            }
            response.setHeader("refresh", "2;url=studentDetailInfo.do?type=student&sno=" + sno);
        }
        if ("tutor".equals(type)) {  // 如果是管理员修改导师的信息
            String tno = request.getParameter("tno");
            String tname = request.getParameter("tname");
            String sex = request.getParameter("sex");
            String birth = request.getParameter("birth");
            String title = request.getParameter("title");
            String ability = request.getParameter("ability");

            if (TutorDAO.getInstance().updateInfoByAdmin(tno, tname, sex, birth, title, ability)) {
                out.println("<h3>修改成功，正在跳转...</h3>");
            }
            response.setHeader("refresh", "2;url=tutorDetailInfo.do?type=tutor&tno=" + tno);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
