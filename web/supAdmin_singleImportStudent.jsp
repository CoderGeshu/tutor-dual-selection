<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/14
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>单个导入学生</title>
    <style>
        .mustInput {
            color: #ff0000;
        }

        #info-wrapper {
            text-align: center;
        }

        table {
            margin: 0 auto;
        }

        td {
            padding: 2px;
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
    <%--  从session中获得信息  --%>
    <c:set var="majors" value="${sessionScope.majors}"/>
    <h2>单个导入学生</h2>
    在这里，您可以实现对单个学生信息的录入，<span class="mustInput">*</span>为必填项。
    <hr>
    <br>
    <div id="info-wrapper">
        <table>
            <tr>
                <td>
                    <label for="sno">
                        <span class="mustInput">*</span>学号：
                    </label>
                </td>
                <td>
                    <input type="text" id="sno" placeholder="如：20171404">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="sname">
                        <span class="mustInput">*</span>姓名：
                    </label>
                </td>
                <td>
                    <input type="text" id="sname" placeholder="如：张三">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="sex">
                        <span class="mustInput">*</span>性别：
                    </label>
                </td>
                <td>
                    <select id="sex">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="birth">
                        <span class="mustInput">*</span>出生日期：
                    </label>
                </td>
                <td>
                    <input type="date" id="birth">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="gpa">
                        <span class="mustInput">*</span>绩点：
                    </label>
                </td>
                <td>
                    <input type="text" id="gpa" placeholder="如：3.240">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="tel">
                        电话：
                    </label>
                </td>
                <td>
                    <input type="text" id="tel" placeholder="如：18316483654">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email">
                        邮箱：
                    </label>
                </td>
                <td>
                    <input type="email" id="email" placeholder="如：zhangsan@126.com">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="major">
                        <span class="mustInput">*</span>专业：

                    </label>
                </td>
                <td>
                    <select id="major">
                        <c:forEach var="major" items="${majors}">
                            <option value="${major.mno}">${major.mname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <br>
        <button class="btn" onclick="return checkInput()">确定录入</button>
    </div>
    <script>
        function checkInput() {
            let sno = document.getElementById('sno').value;
            let sname = document.getElementById('sname').value;
            let sex = document.getElementById('sex').value;
            let birth = document.getElementById('birth').value;
            let gpa = document.getElementById('gpa').value;
            let tel = document.getElementById('tel').value;
            let email = document.getElementById('email').value;
            let mno = document.getElementById('major').value;
            if (sno === "" || sname === "" || sex === "" || birth === "" || gpa === "" || mno === "") {
                alert('必填信息不得为空');
                return false;
            } else {
                window.location.href = 'singleImportStudent.do?op=add&type=student&sno=' + sno +
                    '&sname=' + sname + '&sex=' + sex + '&birth=' + birth + '&gpa=' + gpa +
                    '&tel=' + tel + '&email=' + email + '&mno=' + mno;
            }
        }
    </script>
</body>
</html>
