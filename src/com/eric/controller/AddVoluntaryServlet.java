package com.eric.controller;

import com.eric.dao.VoluntaryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddVoluntaryServlet", urlPatterns = {"/addVoluntary.do"})
public class AddVoluntaryServlet extends HttpServlet {
    // 添加志愿信息
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String sno = (String) session.getAttribute("account");
        // 每次添加志愿都会覆盖上次的所有志愿信息，即先删除当前学生的所有志愿
        VoluntaryDAO.getInstance().deleteVoluntaryBySno(sno);
        // 获得志愿老师编号
        String tno1 = request.getParameter("v1");
        String tno2 = request.getParameter("v2");
        String tno3 = request.getParameter("v3");
        // 重新添加志愿
        VoluntaryDAO.getInstance().addVoluntaryInfo(sno,tno1);
        VoluntaryDAO.getInstance().addVoluntaryInfo(sno,tno2);
        VoluntaryDAO.getInstance().addVoluntaryInfo(sno,tno3);
        // 返回我的志愿页面
        out.println("<h2>填报完成，正在跳转至【我的志愿】页面...</h2>");
        response.setHeader("refresh","2;url=myVoluntary.do");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
