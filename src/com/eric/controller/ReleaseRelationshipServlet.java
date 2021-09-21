package com.eric.controller;

import com.eric.dao.SelectionDAO;
import com.eric.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReleaseRelationshipServlet", urlPatterns = {"/releaseRelation.do"})
public class ReleaseRelationshipServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sno = request.getParameter("sno");
        if (SelectionDAO.getInstance().deleteSelectionBySno(sno)) {
            // 学生表也要清除相应学生的导师号
            StudentDAO.getInstance().releaseRelationship(sno);
            out.println("<script>alert('解除成功！');window.location.href='relation.do?mno=all';</script>");
        } else {
            out.println("<script>alert('解除失败！');window.location.href='relation.do?mno=all';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
