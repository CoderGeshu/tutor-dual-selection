package com.eric.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload.do"})
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 判断上传表单是否为 multipart/form-data 类型
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // 1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // 2. 创建ServletFileUpload对象，并设置上传文件的大小限制
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(10 * 1024 * 1024);  // 以byte为单位，不能超过10M
                upload.setHeaderEncoding("utf-8");

                // 3. 调用parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
                List<FileItem> fileItemList = upload.parseRequest(request);

                // 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
                for (FileItem fileItem : fileItemList) {
                    if (fileItem.isFormField()) {   // 普通表单元素
                        String name = fileItem.getFieldName();  // 获取文件名
                        String value = fileItem.getString("utf-8");  // 设置字符集
                    } else {   // 使用<input type="file">上传的文件的元素
                        String fileName = fileItem.getName();  // 文件名称
                        System.out.println("文件名：" + fileName);
                        // 设置存储的文件路径
                        String realPath = getServletContext().getRealPath("/WEB-INF/upload");

                        // 5. 调用FileItem的write()方法，写入文件
                        File file = new File(realPath, fileName);
                        String absPath = file.getAbsolutePath();
                        System.out.println("文件目录：" + absPath);
                        fileItem.write(file);

                        // 6. 调用FileItem的delete()方法，删除临时文件
                        fileItem.delete();
                        // 重定向到解析表格，并传递参数
                        request.getRequestDispatcher("parseExcel.do?path=" + absPath).forward(request, response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
