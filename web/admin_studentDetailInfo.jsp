<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/7
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员对学生详细信息的操作</title>
    <link rel="stylesheet" href="css/personalInfo.css">
</head>
<body>
    <div id="personal-wrapper">
        <h2>学生详细信息</h2>
        在这里您可以查看或修改学生的详细信息
        <hr>
        <%--   页面从StudentOrTutorDetailInfoServlet转来    --%>
        <%--   从session中获取student实体和DAO实体   --%>
        <c:set var="student" value="${sessionScope.student}"/>
        <c:set var="deptDAO" value="${sessionScope.deptDAO}"/>
        <c:set var="majorDAO" value="${sessionScope.majorDAO}"/>
        <div id="table-wrapper">
            <table>
                <tr>
                    <td class="info">姓名：</td>
                    <td class="rs" id="sname">${student.sname}</td>
                    <td class="info">学号：</td>
                    <td class="rs">${student.sno}</td>
                    <td class="info">性别：</td>
                    <td class="rs" id="sex">${student.sex}</td>
                </tr>
                <tr>
                    <td class="info">出生日期：</td>
                    <td class="rs" id="birth">${student.birth}</td>
                    <td class="info">绩点：</td>
                    <td class="rs" id="gpa">${student.gpa}</td>
                    <td class="info">联系电话：</td>
                    <td class="rs">${student.tel}</td>
                </tr>
                <tr>
                    <td class="info">邮箱：</td>
                    <td class="rs">${student.email}</td>
                    <td class="info">学院：</td>
                    <td class="rs">
                        ${deptDAO.getDeptByDno(majorDAO.getMajorByMno(student.mno).dno).dname}
                    </td>
                    <td class="info">专业：</td>
                    <td class="rs">
                        ${majorDAO.getMajorByMno(student.mno).mname}
                    </td>
                </tr>
                <tr style="padding-top: 50px">
                    <td class="info">
                        个人简介:
                    </td>
                    <td colspan="5" class="rs">
                        <textarea cols="80" rows="3" readonly="readonly">${student.description}</textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="btn">
        <button id="modifyBtn"
                onclick="modify('${student.sname}','${student.birth}','${student.gpa}')">
            修改
        </button>
        <button id="goBackBtn" onclick="goBack('${sessionScope.type}')">返回</button>
        <button id="confirmBtn" onclick="confirmModify('${student.sno}')">确认修改</button>
        <button id="cancelBackBtn" onclick="goBack('${sessionScope.type}')">取消并返回</button>
    </div>
    <script>
        function modify(sname, birth, gpa) {
            document.getElementById('sname').innerHTML = "<input type='text' value='" + sname + "'>";
            document.getElementById('sex').innerHTML = "<select><option>男</option><option>女</option></select>";
            document.getElementById('birth').innerHTML = "<input type='date' value='" + birth + "'>";
            document.getElementById('gpa').innerHTML = "<input type='text' value='" + gpa + "'>";
            document.getElementById('modifyBtn').style.display = 'none';
            document.getElementById('goBackBtn').style.display = 'none';
            document.getElementById('confirmBtn').style.display = 'inline';
            document.getElementById('cancelBackBtn').style.display = 'inline';
        }

        function confirmModify(sno) {
            let newSname = document.getElementById('sname').children[0].value;
            let newSex = document.getElementById('sex').children[0].value;
            let newBirth = document.getElementById('birth').children[0].value;
            let newGpa = document.getElementById('gpa').children[0].value;
            if (newSname === "" || newSex === "" || newBirth === "" || newGpa === "") {
                alert("修改的信息输入不能为空！");
                return false;
            }
            window.location.href = 'modifyStudentInfo.do?type=student&sno=' + sno +
                '&sname=' + newSname + '&sex=' + newSex + '&birth=' +
                newBirth + '&gpa=' + newGpa;
        }

        function goBack(type) {
            if ('admin' === type) {
                window.location.href = 'studentInfoTable.do?type=student&mno=all';
            } else {
                window.location.href = 'supAdminStudentTable.do?type=student&dno=all';
            }
        }
    </script>
</body>
</html>

