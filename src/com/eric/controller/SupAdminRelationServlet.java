package com.eric.controller;

import com.eric.dao.*;
import com.eric.entity.Major;
import com.eric.entity.Selection;
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

// 超管获得关系表
@WebServlet(name = "SupAdminRelationServlet", urlPatterns = {"/supAdminRelation.do"})
public class SupAdminRelationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String dno = request.getParameter("dno");
        // 关系列表
        List<Selection> selections = new ArrayList<>();
        // 对应的每个关系实体
        List<Student> students = new ArrayList<>();
        List<Tutor> tutors = new ArrayList<>();
        if ("all".equals(dno)) {   // 如果查询研究生院所有关系
            // 获得当前所有导师-学生关系表信息
            selections = SelectionDAO.getInstance().getAllSelection();
        } else {       // 否则按照具体的系别号筛选师生关系
            // 首先获取此系别下的所有专业
            List<Major> majors = MajorDAO.getInstance().getMajorsByDno(dno);
            // 再获取每个专业中的导师
            List<Tutor> tutorList = new ArrayList<>();
            for (Major major : majors) {
                tutorList.addAll(TutorDAO.getInstance().getTutorsByMno(major.getMno()));
            }
            // 根据导师编号获得关系表
            for (Tutor  tutor : tutorList) {
                selections.addAll(SelectionDAO.getInstance().getSelectionsByTno(tutor.getTno()));
            }
        }
        // 根据关系表获得每个学生和对应导师的信息
        for (Selection selection : selections) {
            students.add(StudentDAO.getInstance().getStudentBySno(selection.getSno()));
            tutors.add(TutorDAO.getInstance().getTutorByTno(selection.getTno()));
        }
        // 将结果存储到会话
        session.setAttribute("students", students);
        session.setAttribute("tutors", tutors);
        session.setAttribute("deptDAO", DeptDAO.getInstance());
        session.setAttribute("majorDAO", MajorDAO.getInstance());
        // 重定向
        response.sendRedirect("supAdmin_studentAndTutorRelation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
