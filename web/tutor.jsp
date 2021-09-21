<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/6
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导师功能页面</title>
    <link rel="stylesheet" href="css/userUI.css">
</head>
<body>
    <div id="left">
        <nav>
            <span id="logo"><img src="images/logo.jpg" alt="图标"></span>
            <br>
            <div class="function" onclick="changeFunction('tutor_personalInfo.jsp')">
                个人信息
            </div>
            <hr>
            <div class="function" onclick="changeFunction('tutorLeadStudent.do')">
                带领的学生
            </div>
            <hr>
            <div class="function" onclick="changeFunction('tutorVoluntary.do')">
                选我的志愿
            </div>
            <hr>
            <div class="function" onclick="modifyPassword('${sessionScope.tutor.password}')">
                修改密码
            </div>
        </nav>
    </div>

    <div id="right">
        <header>
            <span id="welcome"> ★欢迎您！导师：${sessionScope.tutor.tname}</span>
            <div id="btn-wrapper">
                <button id="btn" onclick="exit()">退出</button>
            </div>
        </header>
        <div id="middle">
            <div id="container">
                <table style="margin: 0 auto;">
                    <iframe src="welcome.jsp" id="myIframe" frameborder="0" width="100%" height="100%">
                    </iframe>
                </table>
            </div>
        </div>
        <footer>
            2020-至今 Eric 版权所有
        </footer>
    </div>
    <script src="js/changeFunction.js"></script>
</body>
</html>
