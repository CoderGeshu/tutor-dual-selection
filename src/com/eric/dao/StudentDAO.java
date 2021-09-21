package com.eric.dao;

import com.eric.entity.Student;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/5/21 21:02
 * @author: Eric
 */
public class StudentDAO {
    private static StudentDAO instance = new StudentDAO();

    private StudentDAO() {
    }

    public static StudentDAO getInstance() {
        return instance;
    }

    public boolean validate(String sno, String password) {
        String mysql = "select * from student where sno=? and password=?";
        System.out.println(mysql);
        return SQLHelper.executeSingleQuery(mysql, sno, password) != null;
    }

    // 获取全部学生
    public List<Student> getAllStudent() {
        String mysql = "select * from student";
        System.out.println(mysql);
        return this.getStudents(mysql);
    }

    // 根据学号获得学生
    public Student getStudentBySno(String sno) {
        String mysql = "select * from student where sno='" + sno + "'";
        System.out.println(mysql);
        return this.getStudents(mysql).get(0);
    }

    // 根据学号及新密码修改学生密码
    public boolean modifyPassword(String sno, String newPassword) {
        String mysql = "update student set password=? where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, newPassword, sno);
    }

    // 用于学生自身更新学生信息
    public boolean updateInfoByStudent(String sno, String birth, String tel, String email, String description) {
        String mysql = "update student set birth=?,tel=?,email=?,description=? where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, birth, tel, email, description, sno);
    }

    // 用于管理员更新学生信息
    public boolean updateInfoByAdmin(String sno, String sname, String sex, String birth, String gpa) {
        String mysql = "update student set sname=?,sex=?,birth=?,gpa=? where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, sname, sex, birth, gpa, sno);
    }

    // 根据专业编号获得学生列表
    public List<Student> getStudentsByMno(String mno) {
        String mysql = "select * from student where mno='" + mno + "'";
        System.out.println(mysql);
        return getStudents(mysql);
    }

    // 确定导师关系后增加导师编号
    public boolean addRelationShip(String sno, String tno) {
        String mysql = "update student set tno=? where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, tno, sno);
    }

    // 解除师生关系后删除导师编号
    public boolean releaseRelationship(String sno) {
        String mysql = "update student set tno=NULL where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, sno);
    }

    // 用于录入学生信息
    public boolean addStudent(Student student) {
        String mysql = "insert into student(sno,password,sname,sex,birth,gpa,tel,email,mno) " +
                "values(?,?,?,?,?,?,?,?,?)";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, student.getSno(), student.getSno(), student.getSname(),
                student.getSex(), student.getBirth(), student.getGpa(), student.getTel(),
                student.getEmail(), student.getMno());
    }

    // 根据学号删除学生信息
    public boolean deleteStudentBySno(String sno) {
        String mysql = "delete from student where sno = ?";
        return SQLHelper.executeUpdate(mysql, sno);
    }


    // 根据sql语句获得学生列表
    private List<Student> getStudents(String mysql) {
        List<Student> students = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString(1));
                student.setPassword(rs.getString(2));
                student.setSname(rs.getString(3));
                student.setSex(rs.getString(4));
                student.setBirth(rs.getString(5));
                student.setGpa(rs.getFloat(6));
                student.setTel(rs.getString(7));
                student.setEmail(rs.getString(8));
                student.setDescription(rs.getString(9));
                student.setMno(rs.getString(10));
                student.setTno(rs.getString(11));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return students;
    }
}
