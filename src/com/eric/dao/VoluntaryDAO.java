package com.eric.dao;

import com.eric.entity.Voluntary;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/6/6 14:45
 * @author: Eric
 */
public class VoluntaryDAO {
    private static VoluntaryDAO instance = new VoluntaryDAO();

    private VoluntaryDAO() {
    }

    public static VoluntaryDAO getInstance() {
        return instance;
    }

    // 向志愿表中插入志愿信息
    public boolean addVoluntaryInfo(String sno, String tno) {
        String mysql = "insert into voluntary(sno,tno,status) values(?,?,?)";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, sno, tno, 0);
    }

    // 根据学生学号删除其志愿表信息
    public boolean deleteVoluntaryBySno(String sno) {
        String mysql = "delete from voluntary where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql,sno);
    }

    // 根据学生学号获得志愿表信息
    public List<Voluntary> getVoluntaryBySno(String sno) {
        String mysql = "select * from voluntary where sno='" + sno + "'";
        System.out.println(mysql);
        return getVoluntaries(mysql);
    }

    // 根据导师编号获得志愿表信息
    public List<Voluntary> getVoluntaryByTno(String tno) {
        String mysql = "select distinct * from voluntary where tno='" + tno + "' and status=0";
        System.out.println(mysql);
        return getVoluntaries(mysql);
    }

    // 根据学生学号删除其所有的志愿信息
    public boolean deleteAllVoluntaryBySno(String sno) {
        String mysql = "delete from voluntary where sno = ?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql,sno);
    }

    // 根据学生学号和导师编号将志愿表状态置为2，表示导师拒绝其请求
    public boolean setVoluntaryStatusFailure(String sno, String tno) {
        String mysql = "update voluntary set status=2 where sno=? and tno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql,sno,tno);
    }

    // 根据sql语句获得志愿表信息列表
    private List<Voluntary> getVoluntaries(String mysql) {
        List<Voluntary> voluntaries = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Voluntary voluntary = new Voluntary();
                voluntary.setSno(rs.getString(1));
                voluntary.setTno(rs.getString(2));
                voluntary.setStatus(rs.getInt(3));
                voluntaries.add(voluntary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return voluntaries;
    }
}
