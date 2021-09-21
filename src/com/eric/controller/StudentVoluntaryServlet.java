package com.eric.controller;

import com.eric.dao.TutorDAO;
import com.eric.dao.VoluntaryDAO;
import com.eric.entity.Tutor;
import com.eric.entity.Voluntary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentVoluntaryServlet", urlPatterns = {"/myVoluntary.do"})
public class StudentVoluntaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sno = (String) session.getAttribute("account");
        List<Voluntary> voluntaries = VoluntaryDAO.getInstance().getVoluntaryBySno(sno);
        session.setAttribute("voluntaries", voluntaries);
        if (!voluntaries.isEmpty()) {
            List<Tutor> tutors = new ArrayList<>();
            for (Voluntary voluntary : voluntaries) {
                Tutor tutor = TutorDAO.getInstance().getTutorByTno(voluntary.getTno());
                tutors.add(tutor);
            }
            session.setAttribute("tutors", tutors);
        }
        // 重定向
        response.sendRedirect("student_myVoluntary.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
