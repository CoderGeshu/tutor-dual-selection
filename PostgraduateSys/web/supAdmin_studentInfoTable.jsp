<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/8
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息列表</title>
    <link rel="stylesheet" href="css/userInfoTable.css">
</head>
<body>
    <div class="title">
        <h1>研究生院学生信息</h1>
    </div>
    <br>
    <div style="text-align: center " class="search-query">
        <label for="dept"><b>按系别查询：</b></label>
        <select name="major" id="dept">
            <option value="all">所有学生</option>
            <c:forEach var="dept" items="${sessionScope.depts}">
                <option value="${dept.dno}">${dept.dname}</option>
            </c:forEach>
        </select>
        <button class="btn" onclick="searchStudentByDept()">查询</button>
        &nbsp;&nbsp;共${sessionScope.students.size()}条信息
    </div>
    <br>
    <table>
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>绩点</th>
            <%--<th>联系电话</th>
            <th>邮箱</th>--%>
            <th>专业</th>
            <th>导师</th>
            <th>删除学生</th>
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
            <td><a href="studentDetailInfo.do?type=student&sno=${student.sno}">${student.sname}</a></td>
            <td>${student.sex}</td>
            <td>${student.birth}</td>
            <td>${student.gpa}</td>
            <%--<td>${student.tel}</td>
            <td>${student.email}</td>--%>
            <td>${sessionScope.majorDAO.getMajorByMno(student.mno).mname}</td>
            <c:if test="${student.tno == null}">
                <td>无</td>
                <td><a href="deleteStudent.do?type=student&sno=${student.sno}">删除</a></td>
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
        function searchStudentByDept() {
            let dno = document.getElementById("dept").value;
            if ("all" === dno) {
                window.location.href = "supAdminStudentTable.do?type=student&dno=all";
            } else {
                window.location.href = "supAdminStudentTable.do?type=student&dno=" + dno;
            }
        }
    </script>
</body>
</html>
