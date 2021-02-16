package com.eric.controller;

import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/deleteStudent.do", "/deleteTutor.do"})
public class DeleteStudentOrTutorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        if ("student".equals(type)) {
            String sno = request.getParameter("sno");
            if (StudentDAO.getInstance().deleteStudentBySno(sno)) {
                out.println("<script>alert('删除成功'); window.location='supAdminStudentTable.do?type=student&dno=all'</script>");
            }
        } else {
            String tno = request.getParameter("tno");
            if (TutorDAO.getInstance().deleteTutorByTno(tno)) {
                out.println("<script>alert('删除成功'); window.location='supAdminTutorTable.do?type=tutor&dno=all'</script>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
