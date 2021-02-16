package com.eric.dao;

import com.eric.entity.Dept;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/5/28 0:04
 * @author: Eric
 */
public class DeptDAO {
    private static DeptDAO instance = new DeptDAO();

    private DeptDAO() {}

    public static DeptDAO getInstance() {
        return instance;
    }

    // 通过系别编号获得系别实体
    public Dept getDeptByDno(String dno) {
        String mysql = "select * from dept where dno='" + dno + "'";
        System.out.println(mysql);
        return this.getDepts(mysql).get(0);
    }

    // 通过管理员编号获得系别实体
    public Dept getDeptByAno(String ano) {
        String mysql = "select * from dept where ano='" + ano + "'";
        System.out.println(mysql);
        return this.getDepts(mysql).get(0);
    }

    // 获得所有系的实体信息
    public List<Dept> getAllDepts() {
        String mysql = "select * from dept";
        System.out.println(mysql);
        return getDepts(mysql);
    }

    // 根据sql语句获得系别（学院）列表
    private List<Dept> getDepts(String mysql) {
        List<Dept> depts = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDno(rs.getString(1));
                dept.setDname(rs.getString(2));
                dept.setAno(rs.getString(3));
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return depts;
    }
}
