<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/6
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.eric.entity.Student" %>
<%@ page import="com.eric.dao.StudentDAO" %>
<html>
<head>
    <title>学生详细信息页面</title>
    <link rel="stylesheet" href="css/personalInfo.css">
</head>
<body>
    <%
        String sno = request.getParameter("sno");
        Student student = StudentDAO.getInstance().getStudentBySno(sno);
    %>
    <div id="personal-wrapper">
        <h2>学生详细信息</h2>
        在这里您可以查看学生的详细信息
        <hr>
        <div id="table-wrapper">
            <table>
                <tr>
                    <td class="info">姓名：</td>
                    <td class="rs"><%= student.getSname() %></td>
                    <td class="info">学号：</td>
                    <td class="rs"><%= student.getSno() %></td>
                    <td class="info">性别：</td>
                    <td class="rs"><%= student.getSex() %></td>
                </tr>
                <tr>
                    <td class="info">出生日期：</td>
                    <td class="rs" id="birth"><%= student.getBirth() %></td>
                    <td class="info">绩点：</td>
                    <td class="rs"><%= student.getGpa() %></td>
                    <td class="info">联系电话：</td>
                    <td class="rs" id="tel"><%= student.getTel() %></td>
                </tr>
                <tr>
                    <td class="info">邮箱：</td>
                    <td class="rs" id="email"><%= student.getEmail() %></td>
                    <td class="info">学院：</td>
                    <td class="rs">${sessionScope.dept.dname}</td>
                    <td class="info">专业：</td>
                    <td class="rs">${sessionScope.major.mname}</td>
                </tr>
                <tr style="padding-top: 50px">
                    <td class="info">
                        个人简介:
                    </td>
                    <td colspan="5" class="rs">
                        <textarea cols="80" rows="3" id="description" readonly="readonly"><%= student.getDescription() %></textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="btn">
        <button id="modifyBtn" onclick="window.history.back();">返回</button>
    </div>
</body>
</html>
