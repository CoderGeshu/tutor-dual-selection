package com.eric.dao;


import com.eric.entity.Tutor;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/5/21 20:55
 * @author: Eric
 */
public class TutorDAO {
    private static TutorDAO instance = new TutorDAO();

    private TutorDAO() {
    }

    public static TutorDAO getInstance() {
        return instance;
    }

    // 验证导师身份信息
    public boolean validate(String tno, String password) {
        String mysql = "select * from tutor where tno=? and password=?";
        System.out.println(mysql);
        return SQLHelper.executeSingleQuery(mysql, tno, password) != null;
    }

    // 导师自己更新导师信息
    public boolean updateInfoByTutor(String tno, String birth, String tel, String email, String description) {
        String mysql = "update tutor set birth=?,tel=?,email=?,description=? where tno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, birth, tel, email, description, tno);
    }

    // 用于管理员更新导师信息
    public boolean updateInfoByAdmin(String tno, String tname, String sex, String birth, String title, String ability) {
        String mysql = "update tutor set tname=?,sex=?,birth=?,title=?,ability=? where tno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, tname, sex, birth, title, ability, tno);
    }

    // 根据导师编号获得导师信息
    public Tutor getTutorByTno(String tno) {
        String mysql = "select * from tutor where tno='" + tno + "'";
        System.out.println(mysql);
        return this.getTutors(mysql).get(0);
    }

    // 根据导师编号及新密码修改导师密码
    public boolean modifyPassword(String tno, String newPassword) {
        String mysql = "update tutor set password=? where tno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, newPassword, tno);
    }

    // 根据导师的所在专业获得导师列表
    public List<Tutor> getTutorsByMno(String mno) {
        String mysql = "select * from tutor where mno='" + mno + "'";
        System.out.println(mysql);
        return this.getTutors(mysql);
    }

    // 根据导师编号获得导师目前所带人数
    public int getCurrentStudentsCountByTno(String tno) {
        String mysql = "select count(*) from selection where tno='" + tno + "'";
        System.out.println(mysql);
        Object obj = SQLHelper.executeSingleQuery(mysql);
        if (obj != null) {
            return Integer.parseInt(obj + "");
        } else {
            return 0;
        }
    }

    // 根据导师编号获得导师的人数上限
    public int getTutorAbilityByTno(String tno) {
        String mysql = "select ability from tutor where tno='" + tno + "'";
        System.out.println(mysql);
        return Integer.parseInt(SQLHelper.executeSingleQuery(mysql) + "");
    }

    // 根据导师编号获得导师目前可带人数
    public int getCanLeadStudentCount(String tno) {
        return this.getTutorAbilityByTno(tno) - this.getCurrentStudentsCountByTno(tno);
    }

    // 根据专业号获得可选导师列表（目前所带人数未满）
    public List<Tutor> getElectiveTutorByMno(String mno) {
        String mysql = "select * from tutor where mno='" + mno + "' and " +
                "ability > (select count(*) from selection where selection.tno = tutor.tno)";
        System.out.println(mysql);
        return getTutors(mysql);
    }

    // 获得所有导师
    public List<Tutor> getAllTutor() {
        String mysql = "select * from tutor";
        System.out.println(mysql);
        return getTutors(mysql);
    }

    // 录入导师信息
    public boolean addTutor(Tutor tutor) {
        String mysql = "insert into tutor(tno,password,tname,sex,birth,title,tel,email,ability,mno) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        return SQLHelper.executeUpdate(mysql, tutor.getTno(), tutor.getTno(),
                tutor.getTname(), tutor.getSex(), tutor.getBirth(), tutor.getTitle(),
                tutor.getTel(), tutor.getEmail(), tutor.getAbility(), tutor.getMno());
    }

    // 根据导师编号删除导师
    public boolean deleteTutorByTno(String tno) {
        String mysql = "delete from tutor where tno = ?";
        return SQLHelper.executeUpdate(mysql, tno);
    }


    // 根据sql语句获得导师列表
    private List<Tutor> getTutors(String mysql) {
        List<Tutor> tutors = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Tutor tutor = new Tutor();
                tutor.setTno(rs.getString(1));
                tutor.setPassword(rs.getString(2));
                tutor.setTname(rs.getString(3));
                tutor.setSex(rs.getString(4));
                tutor.setBirth(rs.getString(5));
                tutor.setTitle(rs.getString(6));
                tutor.setTel(rs.getString(7));
                tutor.setEmail(rs.getString(8));
                tutor.setDescription(rs.getString(9));
                tutor.setAbility(rs.getInt(10));
                tutor.setMno(rs.getString(11));
                tutors.add(tutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return tutors;
    }
}
