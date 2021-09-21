package com.eric.controller;

import com.eric.dao.LogInfoDAO;
import com.eric.entity.LogInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetLogInfoServlet", urlPatterns = "/getLogInfo.do")
public class GetLogInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获得全部登录日志信息
        List<LogInfo> logInfos = LogInfoDAO.getInstance().getAllLogInfo();
        request.getSession().setAttribute("logInfos", logInfos);
        response.sendRedirect("supAdmin_logInfo.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
