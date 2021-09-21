<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/27
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生功能界面</title>
    <link rel="stylesheet" href="css/userUI.css">
</head>
<body>
    <div id="left">
        <nav>
            <span id="logo"><img src="images/logo.jpg" alt="图标"></span>
            <br>
            <div class="function" onclick="changeFunction('student_personalInfo.jsp')">
                个人信息
            </div>
            <hr>
            <div class="function" onclick="changeFunction('studentMajorTutor.do?mno=${sessionScope.student.mno}')">
                专业导师
            </div>
            <hr>
            <div class="function" onclick="myTutor('${sessionScope.tutor.tno}','student_myTutorInfo.jsp')">
                我的导师
            </div>
            <hr>
            <div class="function" onclick="voluntaryOperation('${sessionScope.tutor.tno}','fillVoluntary.do')">
                填报志愿
            </div>
            <hr>
            <div class="function" onclick="voluntaryOperation('${sessionScope.tutor.tno}','myVoluntary.do')">
                我的志愿
            </div>
            <hr>
            <div class="function" onclick="modifyPassword('${sessionScope.student.password}')">
                修改密码
            </div>
        </nav>
    </div>

    <div id="right">
        <header>
            <span id="welcome"> ★欢迎您！学生：${sessionScope.student.sname}</span>
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
