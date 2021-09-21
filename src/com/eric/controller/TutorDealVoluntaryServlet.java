package com.eric.controller;

import com.eric.dao.SelectionDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
import com.eric.dao.VoluntaryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TutorDealVoluntaryServlet", urlPatterns = {"/tutorDealVoluntary.do"})
public class TutorDealVoluntaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        // 获取参数信息
        String tno = (String) session.getAttribute("account");
        String sno = request.getParameter("sno");
        String op = request.getParameter("op");
        // 如果导师接收学生志愿
        if ("receive".equals(op)) {
            // 如果导师目前所带学生已满，则不允许再接收请求
            if (TutorDAO.getInstance().getCanLeadStudentCount(tno) == 0) {
                out.println("<script>alert('您所带人数已满！'); window.location='tutorVoluntary.do';</script>");
            } else { // 否则判断为导师所带学生未满，则添加他们的导师学生关系
                SelectionDAO.getInstance().addTutorAndStudentRelation(sno, tno);
                // 学生表中也要加入导师的导师号
                StudentDAO.getInstance().addRelationShip(sno, tno);
                // 最先接受学生的导师为学生的确定导师，然后就清除学生的所有志愿信息
                VoluntaryDAO.getInstance().deleteVoluntaryBySno(sno);
                out.println("<script>alert('您已接受学生的请求！'); window.location='tutorVoluntary.do';</script>");
            }
        } else if ("refuse".equals(op)) {
            // 如果拒绝学生的请求志愿，则相应的志愿状态值置为2
            if (VoluntaryDAO.getInstance().setVoluntaryStatusFailure(sno, tno)) {
                out.println("<script>alert('您已拒绝学生的请求！'); window.location='tutorVoluntary.do';</script>");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
