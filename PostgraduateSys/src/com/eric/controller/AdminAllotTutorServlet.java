package com.eric.controller;

import com.eric.dao.SelectionDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
import com.eric.dao.VoluntaryDAO;
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

// 管理员为学生分配导师
@WebServlet(name = "AdminAllotTutorServlet", urlPatterns = {"/adminAllotTutor.do"})
public class AdminAllotTutorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        // 获得当前学生学号
        String sno = request.getParameter("sno");
        // 获得操作类型
        String op = request.getParameter("op");
        if ("query".equals(op)) {   // 如果是查询出所有可选导师
            // 获得学生实体
            Student student = StudentDAO.getInstance().getStudentBySno(sno);
            // 根据学生表中的专业号获得其目前可选导师（所带人数未满）列表
            List<Tutor> electiveTutors = TutorDAO.getInstance().getElectiveTutorByMno(student.getMno());
            // 添加到session
            session.setAttribute("student", student);
            session.setAttribute("electiveTutors", electiveTutors);
            session.setAttribute("tutorDAO", TutorDAO.getInstance());
            // 重定向
            response.sendRedirect("admin_allotTutor.jsp");
        }
        if ("allot".equals(op)) {   // 如果是确定分配导师
            // 获得选择的导师编号
            String tno = request.getParameter("tno");
            // 添加关系
            if (SelectionDAO.getInstance().addTutorAndStudentRelation(sno, tno)) {
                // 学生表中也要加入相应的导师编号
                StudentDAO.getInstance().addRelationShip(sno, tno);
                // 删除此学生的志愿信息（以防数据库中仍保留其志愿）
                VoluntaryDAO.getInstance().deleteAllVoluntaryBySno(sno);
                // 分配成功后跳转到学生信息列表
                out.println("<h2>分配成功，正在返回...</h2>");
                response.setHeader("refresh","2;url=studentInfoTable.do?type=student&mno=all");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
