<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/7
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生导师关系</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <%--  从session中读取students和tutors实体列表  --%>
    <c:set var="students" value="${sessionScope.students}" scope="session"/>
    <c:set var="tutors" value="${sessionScope.tutors}" scope="session"/>
    <c:set var="majorDAO" value="${sessionScope.majorDAO}"/>
    <div class="title">
        <h1>当前您系别下确定的学生-导师关系</h1>
    </div>
    <br>
    <div style="text-align: center " class="search-query">
        <b>按专业查询：</b>
        <select name="major" id="major">
            <option value="all">所有关系</option>
            <c:forEach var="major" items="${sessionScope.majors}">
                <option value="${major.mno}">${major.mname}</option>
            </c:forEach>
        </select>
        <button class="btn" onclick="searchRelationByMajor()">查询</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn" onclick="window.location.href = 'exportExcel.do'">导出</button>
        &nbsp;&nbsp;共${students.size()}条信息
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>专业</th>
            <th>学号</th>
            <th>学生姓名</th>
            <th>学生性别</th>
            <th>学生电话</th>
            <th>导师编号</th>
            <th>导师姓名</th>
            <th>导师性别</th>
            <th>导师电话</th>
            <th>解除关系</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="0" scope="page"/>
        <c:forEach var="student" items="${students}">
            <c:set var="tutor" value="${tutors.get(count)}"/>
            <c:if test="${count % 2 == 0}">
                <tr>
            </c:if>
            <c:if test="${count % 2 == 1}">
                <tr class="a1">
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td>${majorDAO.getMajorByMno(student.mno).mname}</td>
            <td>${student.sno}</td>
            <td>${student.sname}</td>
            <td>${student.sex}</td>
            <td>${student.tel}</td>
            <td>${tutor.tno}</td>
            <td>${tutor.tname}</td>
            <td>${tutor.sex}</td>
            <td>${tutor.tel}</td>
            <td><a href='releaseRelation.do?sno=${student.sno}'>解除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        function searchRelationByMajor() {
            let mno = document.getElementById("major").value;
            if ("all" === mno) {
                window.location.href = "relation.do?mno=all";
            } else {
                window.location.href = "relation.do?mno=" + mno;
            }
        }
    </script>
</body>
</html>
