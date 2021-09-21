<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/22
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>院级管理员</title>
    <link rel="stylesheet" href="css/userUI.css">
</head>
<body>
    <div id="left">
        <nav>
            <span id="logo"><img src="images/logo.jpg" alt="图标"></span>
            <br>
            <div class="function" onclick="changeFunction('admin_personalInfo.jsp')">
                个人信息
            </div>
            <hr>
            <div class="function" onclick="changeFunction('studentInfoTable.do?type=student&mno=all')">
                学生管理
            </div>
            <hr>
            <div class="function" onclick="changeFunction('tutorInfoTable.do?type=tutor&mno=all')">
                导师管理
            </div>
            <hr>
            <div class="function" onclick="changeFunction('relation.do?mno=all')">
                学生-导师关系表
            </div>
            <hr>
            <hr>
            <div class="function" onclick="modifyPassword('${sessionScope.admin.password}')">
                修改密码
            </div>
        </nav>
    </div>
    <div id="right">
        <header>
            <span id="welcome"> ★欢迎您！管理员：${sessionScope.admin.aname}</span>
            <div id="btn-wrapper">
                <button id="btn" onclick="exit()">退出</button>
            </div>
        </header>
        <div id="middle">
            <div id="container">
                <table style="margin: 0 auto;">
                    <iframe src="welcome.jsp" id="myIframe" frameborder="0" width="100%" height="100%"></iframe>
                </table>
            </div>
        </div>
        <footer>
            2020 Eric 版权所有
        </footer>
    </div>
    <script src="js/changeFunction.js"></script>
</body>
</html>
