<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/20
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录日志</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <div class="title">
        <h1>用户登录日志信息</h1>
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>账号</th>
            <th>姓名</th>
            <th>操作</th>
            <th>时间</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page"/>
        <c:forEach var="logInfo" items="${sessionScope.logInfos}">
            <c:if test="${count % 2 == 1}">
                <tr>
            </c:if>
            <c:if test="${count % 2 == 0}">
                <tr class="a1">
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td>${logInfo.account}</td>
            <td>${logInfo.name}</td>
            <td>登录系统</td>
            <td>${logInfo.loginTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
