<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/21
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>超管</title>
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
            <div class="function" onclick="changeFunction('supAdminStudentTable.do?type=student&dno=all')">
                学生信息
            </div>
            <hr>
            <div class="function" onclick="changeFunction('supAdminTutorTable.do?type=tutor&dno=all')">
                导师信息
            </div>
            <hr>
            <div class="function" onclick="changeFunction('setOpenTime.do?op=enter')">
                设定志愿开放时间
            </div>
            <hr>
            <div class="function" onclick="changeFunction('supAdminRelation.do?dno=all')">
                学生-导师分配结果
            </div>
            <hr>
            <div class="function" onclick="changeFunction('singleImportStudent.do?type=student')">
                单个学生导入
            </div>
            <hr>
            <div class="function" onclick="changeFunction('singleImportTutor.do?type=tutor')">
                单个导师导入
            </div>
            <hr>
            <div class="function" onclick="changeFunction('supAdmin_importStudent.jsp')">
                批量学生导入
            </div>
            <hr>
            <div class="function" onclick="changeFunction('supAdmin_importTutor.jsp')">
                批量导师导入
            </div>
            <hr>
            <div class="function" onclick="changeFunction('supAdmin_resetUserPassword.jsp')">
                重置用户密码
            </div>
            <hr>
            <div class="function" onclick="modifyPassword('${sessionScope.password}')">
                修改个人密码
            </div>
            <hr>
            <div class="function" onclick="changeFunction('getLogInfo.do')">
                登录日志信息
            </div>
            <hr>
        </nav>
    </div>

    <div id="right">
        <header>
            <span id="welcome"> ★欢迎您！超级管理员：${sessionScope.admin.aname}</span>
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
            Eric 版权所有
        </footer>
    </div>
    <script src="js/changeFunction.js"></script>
</body>
</html>
