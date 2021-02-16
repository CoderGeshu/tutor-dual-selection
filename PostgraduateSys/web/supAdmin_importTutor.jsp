<%--
  Created by IntelliJ IDEA.
  User: Eric
  Date: 2020/6/14
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量导入导师信息</title>
    <style>
        #info-wrapper {
            text-align: center;
        }
        .btn{
            padding: 6px 2em;
            background-color: #2196F3;
            border: 1px solid #2196F3;
            border-radius: 7px;
            color: #ffffff;
            cursor: pointer;
        }
        .em {
            color: #ff0000;
        }
    </style>
</head>
<body>
    <h2>批量导入导师</h2>
    在这里，您可以批量导入导师信息 <br>
    <hr>
    <div id="info-wrapper">
        <!-- 注意： 1. type="file"; 2.method="post" ; 3.enctype="multipart/form-data" -->
        <form id="uploadForm" action="upload.do?type=tutor" method="post" enctype="multipart/form-data">
            <span class="em">请先选择导师信息表➩</span>
            <input type="file" id="uploadFile" name="uploadFile">
            <br/><br/>
            <div class="btn" onclick="checkFileType()">确定</div>
            <hr/>
            <input class="btn" type="reset" value="重置"/>
        </form>
    </div>
    <hr>
    <span class="em">上传说明：</span><br>
    1、文件类型：本页面仅支持上传 <span class="em">.xlsx格式</span> 的文件 <br>
    2、请您将上传文件命名为：<span class="em">tutor.xlsx</span><br>
    3、请使用如下形式的<span class="em">导师信息表结构</span>：<br><br>
    <img src="images/tutorInfoStructure.jpg" alt="导师信息结构">
    <script>
        // 检查文件类型是否正确
        function checkFileType() {
            let filePath = document.getElementById("uploadFile").value;
            let type = getFileType(filePath);
            if (type !== "xlsx") {
                alert("文件类型有误！");
            } else {
                alert("aba");
                let form = document.getElementById("uploadForm");
                form.submit();
            }
        }

        function getFileType(filePath) {
            let startIndex = filePath.lastIndexOf(".");
            if(startIndex !== -1)
                return filePath.substring(startIndex + 1, filePath.length).toLowerCase();
            else return "";
        }
    </script>
</body>
</html>
