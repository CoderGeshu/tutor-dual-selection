<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/21
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>研究生导师互选系统</title>
  <link rel="stylesheet" href="css/loginStyle.css"/>
</head>
<body>
  <div class="form-wrapper">
    <form action="validate.do" id="loginForm" method="post">
      <div class="header">login</div>
      <div class="input-wrapper">
        <div class="border-wrapper">
          <label>
            <input type="text" name="account" placeholder="account" class="border-item" size="40">
          </label>
        </div>
        <div class="border-wrapper">
          <label>
            <input type="password" name="password" placeholder="password" class="border-item" size="40">
          </label>
        </div>
      </div>
      <div class="radio-wrapper">
        <label>
          <input type="radio" name="type" value="student">学生
        </label>
        <label>
          <input type="radio" name="type" value="tutor">导师
        </label>
        <label>
          <input type="radio" name="type" value="admin">系管理员
        </label>
        <label>
          <input type="radio" name="type" value="supAdmin">超管
        </label>
      </div>
      <div class="action">
        <div class="btn" onclick="submitForm()">login</div>
      </div>
      <div class="footer-wrapper">
        <span>2020 Eric 版权所有</span>
      </div>
    </form>
  </div>
  <script src="js/loginCheck.js"></script>
</body>
</html>
