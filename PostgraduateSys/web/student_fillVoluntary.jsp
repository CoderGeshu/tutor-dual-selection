<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/6
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>填报志愿</title>
    <style>
        .btn {
            padding: 6px 2em;
            background-color: #2196F3;
            border: 1px solid #2196F3;
            border-radius: 7px;
            color: #ffffff;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>填报志愿</h1>
    特别注意：未确定导师之前的任何一次提交都会<b>覆盖</b>上一次的志愿信息。
    <hr>
    <br>
    <div style="text-align: center">
        <form action="addVoluntary.do" method="post" id="myForm">
            <label>第一志愿，导师：
                <select style="width: 100px" name="v1" id="v1">
                    <c:forEach var="tutor" items="${sessionScope.electiveTutors}">
                        <option value="${tutor.tno}">${tutor.tname}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br>
            <label>第二志愿，导师：
                <select style="width: 100px" name="v2" id="v2">
                    <c:forEach var="tutor" items="${sessionScope.electiveTutors}">
                        <option value="${tutor.tno}">${tutor.tname}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br>
            <label>第三志愿，导师：
                <select style="width: 100px" name="v3" id="v3">
                    <c:forEach var="tutor" items="${sessionScope.electiveTutors}">
                        <option value="${tutor.tno}">${tutor.tname}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br>
            <button class="btn" onclick="submitForm()">提交</button>
        </form>
    </div>
    <script>
        function submitForm() {
            document.getElementById('myForm').submit();
        }
    </script>
</body>
</html>
