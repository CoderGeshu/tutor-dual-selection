package com.eric.dao;

import com.eric.entity.Admin;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/5/20 22:37
 * @author: Eric
 */
public class AdminDAO {
    private static AdminDAO instance = new AdminDAO();

    private AdminDAO() {}

    public static AdminDAO getInstance() {
        return instance;
    }

    // 验证管理员身份信息
    public boolean validate(String ano, String password, int typeno) {
        String mysql = "select * from admin where ano=? and password=? and typeno=?";
        System.out.println(mysql);
        return SQLHelper.executeSingleQuery(mysql, ano, password, typeno) != null;
    }

    // 根据管理员编号获得管理员
    public Admin getAdminByAno(String ano) {
        String mysql = "select * from admin where ano='" + ano + "'";
        System.out.println(mysql);
        return this.getAdmins(mysql).get(0);
    }

    // 根据管理员编号修改密码
    public boolean modifyPassword(String ano, String newPassword) {
        String mysql = "update admin set password=? where ano=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, newPassword, ano);
    }

    // 管理员更新自身信息
    public boolean updateInfo(String ano, String aname, String sex, String tel) {
        String mysql = "update admin set aname=?,sex=?,tel=? where ano=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, aname, sex, tel, ano);
    }


    // 根据sql语句获得管理员列表
    private List<Admin> getAdmins(String mysql) {
        List<Admin> admins = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAno(rs.getString(1));
                admin.setPassword(rs.getString(2));
                admin.setAname(rs.getString(3));
                admin.setSex(rs.getString(4));
                admin.setTel(rs.getString(5));
                admin.setTypeno(rs.getInt(6));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return admins;
    }
}
