package com.eric.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.eric.dao.DeptDAO;
import com.eric.dao.MajorDAO;
import com.eric.entity.Dept;
import com.eric.entity.Major;
import com.eric.entity.Student;
import com.eric.entity.Tutor;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ExportExcelServlet", urlPatterns = {"/exportExcel.do"})
public class ExportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获得传递的学生表、导师表
        List<Student> students = (List<Student>) session.getAttribute("students");
        List<Tutor> tutors = (List<Tutor>) session.getAttribute("tutors");
        List<List<String>> export = new ArrayList<>();
        // 添加表头
        String[] header = {"学院", "专业", "学号", "学生姓名", "性别", "电话", "导师编号", "导师姓名", "性别", "电话"};
        export.add(Arrays.asList(header));
        for (int i = 0; i < students.size(); ++i) {
            Student student = students.get(i);
            Tutor tutor = tutors.get(i);
            List<String> tmpList = new ArrayList<>();
            Major major = MajorDAO.getInstance().getMajorByMno(student.getMno());
            Dept dept = DeptDAO.getInstance().getDeptByDno(major.getDno());
            tmpList.add(dept.getDname());
            tmpList.add(major.getMname());
            tmpList.add(student.getSno());
            tmpList.add(student.getSname());
            tmpList.add(student.getSex());
            tmpList.add(student.getTel());
            tmpList.add(tutor.getTno());
            tmpList.add(tutor.getTname());
            tmpList.add(tutor.getSex());
            tmpList.add(tutor.getTel());
            export.add(tmpList);
        }
        ExcelWriter writer = ExcelUtil.getWriter();
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(9, "学生-导师关系表");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(export);
        // 获得导出日期
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + df.format(date) + "relation.xls");
        //out为OutputStream，需要写出到的目标流
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
