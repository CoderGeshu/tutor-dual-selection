<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/7
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.eric.entity.Tutor" %>
<%@ page import="com.eric.dao.TutorDAO" %>
<%@ page import="com.eric.dao.MajorDAO" %>
<html>
<head>
    <title>管理员对导师详细信息的操作</title>
    <link rel="stylesheet" href="css/personalInfo.css">
</head>
<body>
    <%--   从session中获取tutor实体和DAO实体   --%>
    <c:set var="tutor" value="${sessionScope.tutor}"/>
    <c:set var="majorDAO" value="${sessionScope.majorDAO}"/>
    <div id="personal-wrapper">
        <h2>导师详细信息</h2>
        在这里您可以查看或修改导师的详细信息
        <hr>
        <div id="table-wrapper">
            <table>
                <tr>
                    <td class="info">姓名：</td>
                    <td class="rs" id="tname">${tutor.tname}</td>
                    <td class="info">账号：</td>
                    <td class="rs">${tutor.tno}</td>
                    <td class="info">性别：</td>
                    <td class="rs" id="sex">${tutor.sex}</td>
                </tr>
                <tr>
                    <td class="info">出生日期：</td>
                    <td class="rs" id="birth">${tutor.birth}</td>
                    <td class="info">职称：</td>
                    <td class="rs" id="title">${tutor.title}</td>
                    <td class="info">联系电话：</td>
                    <td class="rs">${tutor.tel}</td>
                </tr>
                <tr>
                    <td class="info">邮箱：</td>
                    <td class="rs">${tutor.email}</td>
                    <td class="info">专业：</td>
                    <td class="rs">
                        ${majorDAO.getMajorByMno(tutor.mno).mname}
                    </td>
                    <td class="info">人数上限:</td>
                    <td class="rs" id="ability">${tutor.ability}</td>
                </tr>
                <tr style="padding-top: 50px">
                    <td class="info">
                        个人简介:
                    </td>
                    <td colspan="5" class="rs">
                        <textarea cols="80" rows="3" readonly="readonly">${tutor.description}</textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="btn">
        <button id="modifyBtn" onclick="modify('${tutor.tname}','${tutor.birth}','${tutor.ability}')">修改</button>
        <button id="goBackBtn" onclick="goBack('${sessionScope.type}')">返回</button>
        <button id="confirmBtn" onclick="confirmModify('${tutor.tno}')">确认修改</button>
        <button id="cancelBackBtn" onclick="goBack('${sessionScope.type}')">取消并返回</button>
    </div>

    <script>
        function modify(tname, birth, ability) {
            document.getElementById('tname').innerHTML = "<input type='text' value='" + tname + "'>";
            document.getElementById('sex').innerHTML = "<select><option>男</option><option>女</option></select>";
            document.getElementById('birth').innerHTML = "<input type='date' value='" + birth + "'>";
            document.getElementById('title').innerHTML = "<select><option>教授</option><option>副教授</option></select>";
            document.getElementById('ability').innerHTML = "<input type='number' min='3' max='8' value='" + ability + "'>";
            document.getElementById('modifyBtn').style.display = 'none';
            document.getElementById('goBackBtn').style.display = 'none';
            document.getElementById('confirmBtn').style.display = 'inline';
            document.getElementById('cancelBackBtn').style.display = 'inline';
        }

        function confirmModify(tno) {
            let newTname = document.getElementById('tname').children[0].value;
            let newSex = document.getElementById('sex').children[0].value;
            let newBirth = document.getElementById('birth').children[0].value;
            let newTitle = document.getElementById('title').children[0].value;
            let newAbility = document.getElementById('ability').children[0].value;
            if (newTname === "" || newSex === "" || newBirth === "" || newTitle === "" || newAbility === "") {
                alert("修改的信息输入不能为空！");
                return false;
            }
            window.location.href = 'modifyTutorInfo.do?type=tutor&tno=' + tno + '&tname=' + newTname +
                '&sex=' + newSex + '&birth=' + newBirth + '&title=' + newTitle + '&ability=' + newAbility;
        }

        function goBack(type) {
            if ('admin' === type) {
                window.location.href = 'tutorInfoTable.do?type=tutor&mno=all';
            } else {
                window.location.href = 'supAdminTutorTable.do?type=tutor&dno=all';
            }
        }
    </script>

</body>
</html>

