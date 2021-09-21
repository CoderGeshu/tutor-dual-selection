<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/20
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>登录成功</title>
    <link rel="stylesheet" href="css/loginSuccessStyle.css">
</head>
<body>
    <c:if test="${sessionScope.account == null || sessionScope.type == null}">
        <c:redirect url="login.jsp" />
    </c:if>
    <div id="confirm">
        <div id="header-form">
            <h2>恭喜您，登录成功！</h2>
            <div id="redirect"><span id="seconds">3</span>秒后跳转……或点击以下按钮进行跳转</div>
            <br><br>
            <a href="${sessionScope.type}.do">
                <button id="btn">点击跳转</button>
            </a>
        </div>
    </div>
    <div id="end"></div>
    <script>
        let sec = 3;
        function countDown() {
            if (sec > 0) {
                document.getElementById("seconds").innerHTML = --sec + "";
            }
        }
        window.onload = function () {
            setInterval('countDown()', 1000);   // 1s执行一次
        };
        setTimeout("location.href='${sessionScope.type}.do'", 3000); //3s后跳转
    </script>
</body>
</html>
