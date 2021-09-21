<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/6
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.eric.entity.Tutor" %>
<%@ page import="com.eric.dao.TutorDAO" %>
<%@ page import="com.eric.entity.Major" %>
<%@ page import="com.eric.dao.MajorDAO" %>
<html>
<head>
    <title>导师详细信息</title>
    <link rel="stylesheet" href="css/personalInfo.css">
</head>
<body>
    <%
        String tno = request.getParameter("tno");
        Tutor tutor = TutorDAO.getInstance().getTutorByTno(tno);
        Major major = MajorDAO.getInstance().getMajorByMno(tutor.getMno());
    %>
    <div id="personal-wrapper">
        <h2>导师详细信息</h2>
        在这里您可以查看导师的详细信息
        <hr>
        <div id="table-wrapper">
            <table>
                <tr>
                    <td class="info">姓名：</td>
                    <td class="rs"><%= tutor.getTname() %></td>
                    <td class="info">编号：</td>
                    <td class="rs"><%= tutor.getTno() %></td>
                    <td class="info">性别：</td>
                    <td class="rs"><%= tutor.getSex() %></td>
                </tr>
                <tr>
                    <td class="info">出生日期：</td>
                    <td class="rs" id="birth"><%= tutor.getBirth() %></td>
                    <td class="info">职称：</td>
                    <td class="rs"><%= tutor.getTitle() %></td>
                    <td class="info">联系电话：</td>
                    <td class="rs" id="tel"><%= tutor.getTel() %></td>
                </tr>
                <tr>
                    <td class="info">邮箱：</td>
                    <td class="rs" id="email"><%= tutor.getEmail() %></td>
                    <td class="info">学院：</td>
                    <td class="rs">${sessionScope.dept.dname}</td>
                    <td class="info">专业：</td>
                    <td class="rs"><%=major.getMname()%></td>
                </tr>
                <tr style="padding-top: 50px">
                    <td class="info">
                        个人简介:
                    </td>
                    <td colspan="5" class="rs">
                        <textarea cols="80" rows="3" id="description" readonly="readonly"><%= tutor.getDescription() %></textarea>
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