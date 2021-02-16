<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/6
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的志愿信息</title>
</head>
<body>
    <h2>我的志愿</h2>
    <c:if test="${sessionScope.voluntaries.size() == 0}">
        提示：当前您还未填写志愿，请先去填报志愿！
    </c:if>
    <c:if test="${sessionScope.voluntaries.size() != 0}">
        注意：如果要修改志愿信息请前往【填报志愿】重新提交志愿信息！
        <hr>
        <div style="text-align: center;">
            <c:forEach var="i" begin="0" end="2">
                <label>第 <b>${i + 1}</b> 志愿，导师：<input type="text" value="${sessionScope.tutors.get(i).tname}" readonly></label>
                <c:if test="${sessionScope.voluntaries.get(i).status == 0}">
                    <input style="color: green" type="text" value="审核中" readonly size="3">
                </c:if>
                <c:if test="${sessionScope.voluntaries.get(i).status == 2}">
                    <input style="color: red" type="text" value="已拒绝" readonly size="3">
                </c:if>
                <br><br>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
