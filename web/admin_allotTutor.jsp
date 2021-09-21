<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/14
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员为学生分配导师</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <%--  从session中获得当前学生及其可选导师列表  --%>
    <c:set var="student" value="${sessionScope.student}" scope="page"/>
    <c:set var="electiveTutors" value="${sessionScope.electiveTutors}" scope="page"/>
    <%--  页面显示  --%>
    <h2>为学生 ${student.sname} 分配导师</h2>
    在这里，您可以为学生<b>${student.sname}</b>分配其同专业导师，
    如下导师为当前所带人数未满的导师，请您分配！
    <hr>
    <table>
        <thead>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>职称</th>
            <th>联系电话</th>
            <th width="15%">邮箱</th>
            <th width="5%">人数上限</th>
            <th>目前已带人数</th>
            <th>确定分配</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page"/>
        <c:forEach var="tutor" items="${electiveTutors}">
            <c:if test="${count % 2 == 1}">
                <tr>
            </c:if>
            <c:if test="${count % 2 == 0}">
                <tr class="a1">
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td>${tutor.tno}</td>
            <td><a href='student_tutorDetailInfo.jsp?tno=${tutor.tno}'>${tutor.tname}</a></td>
            <td>${tutor.sex}</td>
            <td>${tutor.birth}</td>
            <td>${tutor.title}</td>
            <td>${tutor.tel}</td>
            <td>${tutor.email}</td>
            <td>${tutor.ability}</td>
            <td>${sessionScope.tutorDAO.getCurrentStudentsCountByTno(tutor.tno)}</td>
            <td><a href="adminAllotTutor.do?op=allot&sno=${student.sno}&tno=${tutor.tno}">确认分配</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <button class="btn" onclick="window.history.back()">⇚返回</button>
</body>
</html>
