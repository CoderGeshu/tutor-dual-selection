<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/6
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导师个人信息</title>
    <link rel="stylesheet" href="css/personalInfo.css">
</head>
<body>
    <div id="personal-wrapper">
        <h2>我的信息</h2>
        在这里可以查看或修改自己的信息
        <hr>
        <div id="table-wrapper">
            <table>
                <tr>
                    <td class="info">姓名：</td>
                    <td class="rs">${sessionScope.tutor.tname}</td>
                    <td class="info">账号：</td>
                    <td class="rs">${sessionScope.tutor.tno}</td>
                    <td class="info">性别：</td>
                    <td class="rs">${sessionScope.tutor.sex}</td>
                </tr>
                <tr>
                    <td class="info">出生日期：</td>
                    <td class="rs" id="birth">${sessionScope.tutor.birth}</td>
                    <td class="info">职称：</td>
                    <td class="rs">${sessionScope.tutor.title}</td>
                    <td class="info">联系电话：</td>
                    <td class="rs" id="tel">${sessionScope.tutor.tel}</td>
                </tr>
                <tr>
                    <td class="info">邮箱：</td>
                    <td class="rs" id="email">${sessionScope.tutor.email}</td>
                    <td class="info">学院：</td>
                    <td class="rs">${sessionScope.dept.dname}</td>
                    <td class="info">专业：</td>
                    <td class="rs">${sessionScope.major.mname}</td>
                </tr>
                <tr style="padding-top: 50px">
                    <td class="info">
                        个人简介:
                    </td>
                    <td colspan="5" class="rs">
                        <textarea cols="80" rows="3" id="description"
                                  readonly="readonly">${sessionScope.tutor.description}</textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="btn">
        <button id="modifyBtn" onclick="modify('${sessionScope.tutor.birth}','${sessionScope.tutor.tel}','${sessionScope.tutor.email}')">
            修改
        </button>
        <button id="confirmBtn" onclick="confirmModify('tutor','${sessionScope.tutor.tno}')">
            确认修改
        </button>
        <button id="cancelBackBtn" onclick="goBack('tutor_personalInfo.jsp')">
            取消并返回
        </button>
    </div>
    <script src="js/userModifyPersonalInfo.js"></script>
</body>
</html>