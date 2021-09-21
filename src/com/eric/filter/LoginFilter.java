package com.eric.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/student.jsp","/tutor.jsp","/admin.jsp","/supAdmin.jsp"})
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String account = (String) request.getSession().getAttribute("account");
        String type = (String) request.getSession().getAttribute("type");
        if (account == null || type == null) {
            response.sendRedirect("login.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
