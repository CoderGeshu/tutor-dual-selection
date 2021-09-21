package com.eric.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.eric.dao.MajorDAO;
import com.eric.dao.StudentDAO;
import com.eric.dao.TutorDAO;
import com.eric.entity.Student;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// 解析excel表格
@WebServlet(name = "ParseExcelServlet", urlPatterns = {"/parseExcel.do"})
public class ParseExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获得导入类型：学生或导师
        String type = request.getParameter("type");
        // 获得文件绝对路径
        String path = request.getParameter("path");
        // 读取文件，返回一个列表的列表，存储对象为Object类型
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> readAll = reader.read();
        readAll.remove(0);// 把首行的头部去掉
        // 如果导入的是学生信息
        if ("student".equals(type)) {
            for (List<Object> list : readAll) {
                // 设置每个学生的实体信息
                Student student = new Student();
                student.setSno(list.get(0) + "");
                student.setSname(list.get(1) + "");
                student.setSex(list.get(2) + "");
                student.setBirth(list.get(3) + "");
                student.setGpa(Float.parseFloat(list.get(4) + ""));
                student.setTel(list.get(5) + "");
                student.setEmail(list.get(6) + "");
                String mname = list.get(7) + "";
                String mno = MajorDAO.getInstance().getMajorByMname(mname).getMno();
                student.setMno(mno);
                // 向数据库中添加学生信息
                StudentDAO.getInstance().addStudent(student);
            }
            out.println("<h2>导入成功，正在跳转...</h2>");
            response.setHeader("refresh", "2;url=supAdmin_importStudent.jsp");
        }

        // 如果导入的是导师信息
        if ("tutor".equals(type)) {
            for (List<Object> list : readAll) {
                // 设置每个导师的实体信息
                Tutor tutor = new Tutor();
                tutor.setTno(list.get(0) + "");
                tutor.setTname(list.get(1) + "");
                tutor.setSex(list.get(2) + "");
                tutor.setBirth(list.get(3) + "");
                tutor.setTitle(list.get(4) + "");
                tutor.setTel(list.get(5) + "");
                tutor.setEmail(list.get(6) + "");
                tutor.setAbility(Integer.parseInt(list.get(7) + ""));
                String mname = list.get(8) + "";
                String mno = MajorDAO.getInstance().getMajorByMname(mname).getMno();
                tutor.setMno(mno);
                // 向数据库中添加导师信息
                TutorDAO.getInstance().addTutor(tutor);
            }
            out.println("<h2>导入成功，正在跳转...</h2>");
            response.setHeader("refresh", "2;url=supAdmin_importTutor.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
