package com.eric.dao;

import com.eric.util.SQLHelper;

/**
 * @Date: 2020/6/15 10:55
 * @author: Eric
 */
public class OpenTimeDAO {
    private static OpenTimeDAO instance = new OpenTimeDAO();

    private OpenTimeDAO() {}

    public static OpenTimeDAO getInstance() {
        return instance;
    }

    public boolean setOpenTime(String startTime, String endTime) {
        this.deleteOpenTime();
        String mysql = "insert into opentime values(?,?)";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, startTime, endTime);
    }

    public String getStartTime() {
        String mysql = "select starttime from opentime";
        System.out.println(mysql);
        return SQLHelper.executeSingleQuery(mysql) + "";
    }

    public String getEndTime() {
        String mysql = "select endtime from opentime";
        System.out.println(mysql);
        return SQLHelper.executeSingleQuery(mysql) + "";
    }

    private boolean deleteOpenTime() {
        String mysql = "delete from opentime";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql);
    }
}
