<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/8
  Time: 16:40
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
    <%--  从SupAdminRelationServlet转来  --%>
    <%--  从session中获取值  --%>
    <c:set var="students" value="${sessionScope.students}" scope="session"/>
    <c:set var="tutors" value="${sessionScope.tutors}" scope="session"/>
    <c:set var="deptDAO" value="${sessionScope.deptDAO}"/>
    <c:set var="majorDAO" value="${sessionScope.majorDAO}"/>

    <div class="title">
        <h1>学生-导师关系表</h1>
    </div>
    <br>
    <div style="text-align: center " class="search-query">
        <label for="dept"><b>按系别查询：</b></label>
        <select name="major" id="dept">
            <option value="all">所有关系</option>
            <c:forEach var="dept" items="${sessionScope.depts}">
                <option value="${dept.dno}">${dept.dname}</option>
            </c:forEach>
        </select>
        <button class="btn" onclick="searchRelationByDept()">查询</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn" onclick="window.location.href = 'exportExcel.do'">导出</button>
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>学院</th>
            <th>专业</th>
            <th>学号</th>
            <th>学生姓名</th>
            <th>学生性别</th>
            <th>学生电话</th>
            <th>导师编号</th>
            <th>导师姓名</th>
            <th>导师性别</th>
            <th>导师电话</th>
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
            <td>${deptDAO.getDeptByDno(majorDAO.getMajorByMno(student.mno).dno).dname}</td>
            <td>${majorDAO.getMajorByMno(student.mno).mname}</td>
            <td>${student.sno}</td>
            <td>${student.sname}</td>
            <td>${student.sex}</td>
            <td>${student.tel}</td>
            <td>${tutor.tno}</td>
            <td>${tutor.tname}</td>
            <td>${tutor.sex}</td>
            <td>${tutor.tel}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        function searchRelationByDept() {
            let dno = document.getElementById("dept").value;
            if ("all" === dno) {
                window.location.href = "supAdminRelation.do?dno=all";
            } else {
                window.location.href = "supAdminRelation.do?dno=" + dno;
            }
        }
    </script>
</body>
</html>
