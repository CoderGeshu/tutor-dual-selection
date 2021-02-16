package com.eric.controller;

import com.eric.dao.SelectionDAO;
import com.eric.dao.StudentDAO;
import com.eric.entity.Selection;
import com.eric.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TutorLeadStudentServlet", urlPatterns = {"/tutorLeadStudent.do"})
public class TutorLeadStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获得导师编号
        String tno = (String) session.getAttribute("account");
        // 从关系表中根据导师编号检索出所有关系实体
        List<Selection> selections = SelectionDAO.getInstance().getSelectionsByTno(tno);
        // 学生列表添加每个关系中的学生实体
        List<Student> students = new ArrayList<>();
        for (Selection selection : selections) {
            students.add(StudentDAO.getInstance().getStudentBySno(selection.getSno()));
        }
        // 把学生列表设置为会话存储
        session.setAttribute("students", students);
        // 跳转显示当前导师所带领的学生信息页面
        response.sendRedirect("tutor_leadStudent.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
