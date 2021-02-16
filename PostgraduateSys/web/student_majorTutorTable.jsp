<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/28
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>导师信息表格</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <div class="title">
        <h1>您专业的导师信息</h1>
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>姓名</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>职称</th>
            <th>联系电话</th>
            <th>邮箱</th>
            <th>人数上限</th>
            <th>目前已带人数</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page"/>
        <c:forEach var="tutor" items="${sessionScope.tutors}">
            <c:if test="${count % 2 == 1}">
                <tr>
            </c:if>
            <c:if test="${count % 2 == 0}">
                <tr class="a1">
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td><a href='student_tutorDetailInfo.jsp?tno=${tutor.tno}'>${tutor.tname}</a></td>
            <td>${tutor.sex}</td>
            <td>${tutor.birth}</td>
            <td>${tutor.title}</td>
            <td>${tutor.tel}</td>
            <td>${tutor.email}</td>
            <td>${tutor.ability}</td>
            <td>${sessionScope.tutorDAO.getCurrentStudentsCountByTno(tutor.tno)}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
