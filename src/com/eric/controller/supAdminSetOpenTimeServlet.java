package com.eric.controller;

import com.eric.dao.OpenTimeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "supAdminSetOpenTimeServlet", urlPatterns = {"/setOpenTime.do"})
public class supAdminSetOpenTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String op = request.getParameter("op");
        if ("enter".equals(op)) {
            String startTime = OpenTimeDAO.getInstance().getStartTime();
            String endTime = OpenTimeDAO.getInstance().getEndTime();
            session.setAttribute("startTime",startTime);
            session.setAttribute("endTime",endTime);
            response.sendRedirect("supAdmin_setOpenTime.jsp");
        }
        if ("set".equals(op)) {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            OpenTimeDAO.getInstance().setOpenTime(startTime, endTime);
            session.setAttribute("startTime",startTime);
            session.setAttribute("endTime",endTime);
            out.println("<h2>设定成功，正在返回..</h2>");
            response.setHeader("refresh","2;url=supAdmin_setOpenTime.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
