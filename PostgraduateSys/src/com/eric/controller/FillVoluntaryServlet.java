package com.eric.controller;

import com.eric.dao.OpenTimeDAO;
import com.eric.dao.TutorDAO;
import com.eric.entity.Major;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "FillVoluntaryServlet", urlPatterns = {"/fillVoluntary.do"})
public class FillVoluntaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获得当前日期
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(date);
        // 从数据库中获取填报志愿的起止时间
        String startTime = OpenTimeDAO.getInstance().getStartTime();
        String endTime = OpenTimeDAO.getInstance().getEndTime();
        if (today.compareTo(startTime) < 0 || today.compareTo(endTime) > 0) {
            out.println("<h2>当前时间不在填报志愿的时间段，正在返回...</h2>");
            response.setHeader("refresh","3;url=welcome.jsp");
        }else {
            // 否则就是在填报志愿时间段，进入填报志愿的页面
            HttpSession session = request.getSession();
            Major major = (Major) session.getAttribute("major");
            // 根据专业获得相应的可选择（目前所带人数未满）的导师列表
            List<Tutor> electiveTutors = TutorDAO.getInstance().getElectiveTutorByMno(major.getMno());
            session.setAttribute("electiveTutors", electiveTutors);
            response.sendRedirect("student_fillVoluntary.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
