<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/22
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" href="css/personalInfo.css">
</head>
<body>
    <div id="personal-wrapper">
        <h2>我的信息</h2>
        在这里，您可以查看或修改自己的信息
        <hr>
        <%--   从session中获取admin实体     --%>
        <c:set var="admin" value="${sessionScope.admin}"/>
        <div id="table-wrapper">
            <table>
                <tr>
                    <td class="info">姓名：</td>
                    <td class="rs" id="aname">${admin.aname}</td>
                    <td class="info">账号：</td>
                    <td class="rs">${admin.ano}</td>
                    <td class="info">性别：</td>
                    <td class="rs" id="sex">${admin.sex}</td>
                </tr>
                <tr>
                    <td class="info">联系电话：</td>
                    <td class="rs" id="tel">${admin.tel}</td>
                    <td class="info">类型：</td>
                    <td class="rs">${sessionScope.typeName}</td>
                    <td class="info">负责系别：</td>
                    <td class="rs">${sessionScope.dept.dname}</td>
                </tr>
            </table>
        </div>
    </div>
    <div id="btn">
        <button id="modifyBtn" onclick="modify('${admin.aname}','${admin.tel}')">修改</button>
        <button id="confirmBtn" onclick="confirmModify('${admin.ano}')">确认修改</button>
        <button id="cancelBackBtn" onclick="goBack()">取消</button>
    </div>
    <script src="js/adminModifyPersonalInfo.js"></script>
</body>
</html>
