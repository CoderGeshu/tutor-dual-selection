package com.eric.controller;

import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
import com.eric.dao.VoluntaryDAO;
import com.eric.entity.Student;
import com.eric.entity.Voluntary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TutorVoluntaryServlet", urlPatterns = {"/tutorVoluntary.do"})
public class TutorVoluntaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tno = (String) session.getAttribute("account");
        // 获得当前导师目前可带人数
        int canLeadStudentCount = TutorDAO.getInstance().getCanLeadStudentCount(tno);
        session.setAttribute("canLeadStudentCount", canLeadStudentCount);
        // 获得选择此导师的志愿信息（学生信息）
        List<Voluntary> voluntaries = VoluntaryDAO.getInstance().getVoluntaryByTno(tno);
        List<Student> students = new ArrayList<>();
        for (Voluntary voluntary : voluntaries) {
            students.add(StudentDAO.getInstance().getStudentBySno(voluntary.getSno()));
        }
        session.setAttribute("students", students);
        // 重定向到学生志愿表信息页面
        response.sendRedirect("tutor_studentVoluntaryTable.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
