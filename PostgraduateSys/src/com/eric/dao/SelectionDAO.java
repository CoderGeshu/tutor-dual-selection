package com.eric.dao;

import com.eric.entity.Selection;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/5/27 23:02
 * @author: Eric
 */

public class SelectionDAO {
    private static SelectionDAO instance = new SelectionDAO();

    private SelectionDAO() {
    }

    public static SelectionDAO getInstance() {
        return instance;
    }

    // 向选择关系表中增添确定的导师学生关系
    public boolean addTutorAndStudentRelation(String sno, String tno) {
        String mysql = "insert into selection values(?,?)";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, sno, tno);
    }

    // 通过导师编号获得选择关系信息
    public List<Selection> getSelectionsByTno(String tno) {
        String mysql = "select * from selection where tno='" + tno + "'";
        System.out.println(mysql);
        return getSelections(mysql);
    }

    // 获得所有师生关系表信息
    public List<Selection> getAllSelection() {
        String mysql = "select * from selection";
        System.out.println(mysql);
        return getSelections(mysql);
    }

    // 根据学号删除对应的学生-导师关系
    public boolean deleteSelectionBySno(String sno) {
        String mysql = "delete from selection where sno=?";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, sno);
    }

    // 通过sql语句获得选择关系
    private List<Selection> getSelections(String mysql) {
        List<Selection> selections = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                Selection selection = new Selection();
                selection.setSno(rs.getString(1));
                selection.setTno(rs.getString(2));
                selections.add(selection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return selections;
    }
}
