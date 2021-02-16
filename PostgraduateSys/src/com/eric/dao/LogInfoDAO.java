package com.eric.dao;

import com.eric.entity.LogInfo;
import com.eric.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/6/20 21:47
 * @author: Eric
 */
public class LogInfoDAO {
    private static LogInfoDAO instance = new LogInfoDAO();

    private LogInfoDAO() {
    }

    public static LogInfoDAO getInstance() {
        return instance;
    }

    // 添加登录日志信息
    public boolean addLogInfo(String account, String name) {
        String mysql = "insert into loginfo values(?,?,now())";
        System.out.println(mysql);
        return SQLHelper.executeUpdate(mysql, account, name);
    }

    // 以登录日期降序排列获得全部登录日志信息
    public List<LogInfo> getAllLogInfo() {
        String mysql = "select * from loginfo order by logintime desc";
        System.out.println(mysql);
        return this.getLogInfos(mysql);
    }

    // 根据sql语句获得登录日志信息
    private List<LogInfo> getLogInfos(String mysql) {
        List<LogInfo> logInfos = new ArrayList<>();
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            while (rs.next()) {
                LogInfo logInfo = new LogInfo();
                logInfo.setAccount(rs.getString(1));
                logInfo.setName(rs.getString(2));
                logInfo.setLoginTime(rs.getString(3));
                logInfos.add(logInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHelper.closeConnection();
        }
        return logInfos;
    }
}
