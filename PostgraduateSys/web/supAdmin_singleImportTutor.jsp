<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/14
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>单个录入导师</title>
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
    <h2>单个导入导师</h2>
    在这里，您可以实现对单个导师信息的录入，<span class="mustInput">*</span>为必填项。
    <hr>
    <br>
    <div id="info-wrapper">
        <table>
            <tr>
                <td>
                    <label for="tno">
                        <span class="mustInput">*</span>编号：
                    </label>
                </td>
                <td>
                    <input type="text" id="tno" placeholder="如：t20071014">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="tname">
                        <span class="mustInput">*</span>姓名：
                    </label>
                </td>
                <td>
                    <input type="text" id="tname" placeholder="如：李四">
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
                    <label for="title">
                        <span class="mustInput">*</span>职称：
                    </label>
                </td>
                <td>
                    <select id="title">
                        <option value="教授">教授</option>
                        <option value="副教授">副教授</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="tel">
                        电话：
                    </label>
                </td>
                <td>
                    <input type="text" id="tel" placeholder="如：18317061456">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email">
                        邮箱：
                    </label>
                </td>
                <td>
                    <input type="email" id="email" placeholder="如：lisi@126.com">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ability">
                        <span class="mustInput">*</span>带领学生上限
                    </label>
                </td>
                <td>
                    <input type="number" value="3" min="3" max="8" id="ability">
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
            let tno = document.getElementById('tno').value;
            let tname = document.getElementById('tname').value;
            let sex = document.getElementById('sex').value;
            let birth = document.getElementById('birth').value;
            let title = document.getElementById('title').value;
            let tel = document.getElementById('tel').value;
            let email = document.getElementById('email').value;
            let ability = document.getElementById("ability").value;
            let mno = document.getElementById('major').value;
            if (tno === "" || tname === "" || sex === "" || birth === "" || title === "" || ability === "" || mno === "") {
                alert('必填信息不得为空');
                return false;
            } else {
                window.location.href = 'singleImportTutor.do?op=add&type=tutor&tno=' + tno +
                    '&tname=' + tname + '&sex=' + sex + '&birth=' + birth + '&title=' + title +
                    '&tel=' + tel + '&email=' + email + '&ability=' + ability + '&mno=' + mno;
            }
        }
    </script>
</body>
</html>
