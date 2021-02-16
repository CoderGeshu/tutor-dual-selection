<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/15
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>设定志愿开放时间</title>
    <style>
        .btn {
            padding: 3px 1em;
            background-color: #2196F3;
            border: 1px solid #2196F3;
            border-radius: 7px;
            color: #ffffff;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <%--  获取session值  --%>
    <c:set var="startTime" value="${sessionScope.startTime}"/>
    <c:set var="endTime" value="${sessionScope.endTime}"/>
    <h2>设置志愿开放时间</h2>
    在这里，您可以设定学生填报志愿的时间段
    <hr>
    <h4></h4>
    <div style="margin-top: 30px;text-align: center;">
        <form action="setOpenTime.do?op=set" method="post">
            <b>
                <label>开始时间：<input type="date" value="${startTime}" name="startTime"></label>
            </b>
            <br>↿⇂<br>
            <b>
                <label>结束时间：<input type="date" value="${endTime}" name="endTime"></label>
            </b>
            <br><br>
            <input class="btn" type="submit" value="确定">
        </form>
    </div>
</body>
</html>
