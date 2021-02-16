package com.eric.controller;

import com.eric.dao.MajorDAO;
import com.eric.dao.SelectionDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
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

@WebServlet(name = "StudentAndTutorRelationServlet", urlPatterns = {"/relation.do"})
public class StudentAndTutorRelationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取要查询的专业号
        String mno = request.getParameter("mno");
        // 关系表，以及每个学生和导师的实体列表
        List<Selection> selections = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Tutor> tutors = new ArrayList<>();
        List<Tutor> tmpTutors = new ArrayList<>();
        if ("all".equals(mno)) {   // 如果是查询本系别下所有专业关系
            // 获得此系别下的所有专业列表
            List<Major> majors = (List<Major>) session.getAttribute("majors");
            // 获得每个专业中的导师列表
            for (Major major : majors) {
                tmpTutors.addAll(TutorDAO.getInstance().getTutorsByMno(major.getMno()));
            }
        } else {       // 否则是按照某一专业号来获得师生关系
            // 根据专业号获取导师列表
            tmpTutors = TutorDAO.getInstance().getTutorsByMno(mno);
        }
        // 根据导师编号获得当前所有导师-学生关系表信息
        for (Tutor tutor : tmpTutors) {
            selections.addAll(SelectionDAO.getInstance().getSelectionsByTno(tutor.getTno()));
        }
        // 根据关系表获得具体的导师和学生列表
        for (Selection selection : selections) {
            students.add(StudentDAO.getInstance().getStudentBySno(selection.getSno()));
            tutors.add(TutorDAO.getInstance().getTutorByTno(selection.getTno()));
        }
        // 将列表添加到会话
        session.setAttribute("students", students);
        session.setAttribute("tutors", tutors);
        session.setAttribute("majorDAO", MajorDAO.getInstance());
        // 重定向
        response.sendRedirect("admin_studentAndTutorRelation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
