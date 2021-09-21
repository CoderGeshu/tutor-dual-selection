<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/5/28
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <style>
        #wrapper {
            text-align: center;
            padding-top: 30px;
        }

        table {
            margin: 0 auto;
            text-align: right;
        }

        table tr td {
            padding: 5px;
        }

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
    <h2>修改密码</h2>
    在这里可以修改自己的密码
    <hr>
    <div id="wrapper">
        <form action="modifyPassword.do" method="post" id="myForm">
            <table>
                <tr>
                    <td>账号：</td>
                    <td>
                        <label>
                            <input type="text" value="${sessionScope.account}" readonly>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td>
                        <label>
                            <input type="password" name="newPassword" id="pwd1" placeholder="输入新密码">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td>
                        <label>
                            <input type="password" id="pwd2" placeholder="确认新密码">
                        </label>
                    </td>
                </tr>
            </table>
        </form>
        <button class="btn" onclick="checkInput()">确认修改</button>
        <button class="btn" onclick="window.history.back();">取消并返回</button>
    </div>
    <script>
        function checkInput() {
            let pwd1 = document.getElementById('pwd1').value;
            let pwd2 = document.getElementById('pwd2').value;
            if (pwd1 === "" || pwd2 === "") {
                alert('新密码输入不能为空，请重新输入。');
                return false;
            } else if (pwd1 !== pwd2) {
                alert('两次输入密码不一致，请重新输入。');
                return false;
            } else {
                let myForm = document.getElementById('myForm');
                myForm.submit();
            }
        }
    </script>
</body>
</html>
