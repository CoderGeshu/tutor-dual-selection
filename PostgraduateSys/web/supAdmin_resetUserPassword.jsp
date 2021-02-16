<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/8
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置用户密码</title>
    <style>
        #wrapper {
            padding-top: 20px;
            text-align: center;
        }
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
    <h2>重置用户密码</h2>
    在这里，您可以恢复不同用户的密码为初始值
    <hr>
    <div id="wrapper">
        <form action="resetPassword.do" method="post" id="myForm">
            <label>
                请选择要重置密码的账号类型：
                <select name="type">
                    <option value="student">学生</option>
                    <option value="tutor">导师</option>
                    <option value="admin">院管理员</option>
                </select>
            </label>
            <br><br>
            <label>请输入要重置密码的账号：<input type="text" id="resetAccount" name="resetAccount" placeholder="学号/导师编号/管理员账号"></label>
            <br><br>
            <button class="btn" onclick="return checkInput()">确认重置</button>
        </form>
    </div>
    <script>
        function checkInput() {
            let resetAccount = document.getElementById("resetAccount").value;
            if (resetAccount === "" || resetAccount == null) {
                alert("输入不能为空");
                return false;
            } else {
                let myForm = document.getElementById("myForm");
                myForm.submit();
            }
        }
    </script>
</body>
</html>
