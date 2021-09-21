<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/25
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <div class="title">
        <h1>您系别下的学生信息</h1>
    </div>
    <br>
    <div style="text-align: center " class="search-query">
        <b>按专业查询：</b>
        <select name="major" id="major">
            <option value="all">所有学生</option>
            <c:forEach var="major" items="${sessionScope.majors}">
                <option value="${major.mno}">${major.mname}</option>
            </c:forEach>
        </select>
        <button class="btn" onclick="searchStudentByMajor()">查询</button>
        &nbsp;&nbsp;共${sessionScope.students.size()}条信息
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th width="5%">性别</th>
            <%--<th>出生日期</th>--%>
            <th>绩点</th>
            <%--<th>联系电话</th>--%>
            <th width="15%">邮箱</th>
            <th>专业</th>
            <th>导师</th>
            <th>分配导师</th>
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
            <td>${student.sno}</td>
            <td><a href='studentDetailInfo.do?type=student&sno=${student.sno}'>${student.sname}</a></td>
            <td>${student.sex}</td>
            <%--<td>${student.birth}</td>--%>
            <td>${student.gpa}</td>
            <%--<td>${student.tel}</td>--%>
            <td>${student.email}</td>
            <td>${sessionScope.majorDAO.getMajorByMno(student.mno).mname}</td>
            <c:if test="${student.tno == null}">
                <td>无</td>
                <td><a href="adminAllotTutor.do?op=query&sno=${student.sno}">分配</a></td>
            </c:if>
            <c:if test="${student.tno != null}">
                <td>${sessionScope.tutorDAO.getTutorByTno(student.tno).tname}</td>
                <td>--</td>
            </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        function searchStudentByMajor() {
            let mno = document.getElementById("major").value;
            if ("all" === mno) {
                window.location.href = "studentInfoTable.do?type=student&mno=all";
            } else {
                window.location.href = "studentInfoTable.do?type=student&mno=" + mno;
            }
        }
    </script>
</body>
</html>
