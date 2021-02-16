<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/7
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的学生</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <div class="title">
        <h1>您所带领的学生信息</h1>
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>姓名</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>绩点</th>
            <th>联系电话</th>
            <th>邮箱</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page"/>
        <c:forEach var="student" items="${sessionScope.students}">
            <c:if test="${count % 2 == 1}">
                <tr>
            </c:if>
            <c:if test="${count % 2 == 0}">
                <tr class="a1">
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td><a href='tutor_studentDetailInfo.jsp?sno=${student.sno}'>${student.sname}</a></td>
            <td>${student.sex}</td>
            <td>${student.birth}</td>
            <td>${student.gpa}</td>
            <td>${student.tel}</td>
            <td>${student.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>

