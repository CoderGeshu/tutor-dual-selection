package com.eric.dao;

import com.eric.entity.Major;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/5/27 23:02
 * @author: Eric
 */
public class MajorDAO {
    private static MajorDAO instance = new MajorDAO();

    private MajorDAO() {}

    public static MajorDAO getInstance() {
        return instance;
    }

    // 获得所有专业信息
    public List<Major> getAllMajors() {
        String mysql = "select * from major";
        System.out.println(mysql);
        return this.getMajors(mysql);
    }

    // 根据专业编号获得专业信息
    public Major getMajorByMno(String mno) {
        String mysql = "select * from major where mno='" + mno + "'";
        System.out.println(mysql);
        return this.getMajors(mysql).get(0);
    }

    // 根据系别号获得专业信息列表
    public List<Major> getMajorsByDno(String dno) {
        String mysql = "select * from major where dno='" + dno + "'";
        System.out.println(mysql);
        return getMajors(mysql);
    }

    // 根据专业名称获得专业
    public Major getMajorByMname(String mname) {
        String mysql = "select * from major where mname='"+mname+"'";
        System.out.println(mysql);
        return this.getMajors(mysql).get(0);
    }

    // 根据sql语句获得专业信息列表
    private List<Major> getMajors(String mysql) {
        List<Major> majors = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Major major = new Major();
                major.setMno(rs.getString(1));
                major.setMname(rs.getString(2));
                major.setDno(rs.getString(3));
                majors.add(major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return majors;
    }
}
