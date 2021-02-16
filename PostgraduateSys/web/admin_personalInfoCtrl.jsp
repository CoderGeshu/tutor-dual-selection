<%@ page import="com.eric.dao.AdminDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/7
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员修改个人信息</title>
</head>
<body>
    <%
        String ano = request.getParameter("ano");
        String aname = request.getParameter("aname");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");

        AdminDAO.getInstance().updateInfo(ano, aname, sex, tel);
        out.println("<h3>修改成功，正在跳转...</h3>");
        session.setAttribute("admin", AdminDAO.getInstance().getAdminByAno(ano));
    %>
    <script>
        setTimeout("window.location.href='admin_personalInfo.jsp'", 2000); //2s后跳转
    </script>
</body>
</html>
