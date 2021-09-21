package com.eric.util;

import java.sql.*;

/**
 * 连接数据库
 *
 * @Date: 2020/5/20 22:13
 * @author: Eric
 */
public class SQLHelper {

    //创建数据库连接、预处理和结果集对象
    private static Connection conn = null;
    private static PreparedStatement pre = null;
    private static ResultSet rs = null;

    //加载驱动
    static {
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接
     */
    private static  void buildConnection() {
        //数据库配置信息
        String url = "jdbc:mysql://localhost:3306/tutor_dual_selection";
        String user = "root", password = "123456";
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行查询 SQL 语句，返回查询结果集
     *
     * @param sql SQL 语句
     * @return 结果集对象
     */
    public static ResultSet executeQuery(String sql) {
        buildConnection();
        try {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //需要在调用处手动关闭连接
        return rs;
    }

//    public static boolean executeUpdate(String mysql) {
//        buildConnection();
//        int r = 0;
//        try {
//            pre = conn.prepareStatement(mysql);
//            r = pre.executeUpdate();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return r != 0;
//    }

    /**
     * 执行可变参数的更新语句
     *
     * @param mysql  更新语句
     * @param params 可变参数
     * @return 是否更新成功
     */
    public static boolean executeUpdate(String mysql, Object... params) {
        buildConnection();
        int r = 0;
        try {
            pre = conn.prepareStatement(mysql);
            int count = 1;
            for (Object param : params) {
                if (param instanceof String) {
                    pre.setString(count++, param.toString());
                } else if (param instanceof Integer) {
                    pre.setInt(count++, new Integer(param.toString()));
                } else if (param instanceof Double) {
                    pre.setDouble(count++, new Double(param.toString()));
                } else if (param instanceof Float) {
                    pre.setFloat(count++, new Float(param.toString()));
                } else {
                    pre.setObject(count++, param);
                }
            }
            r = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return r != 0;
    }

//    public static Object executeSingleQuery(String singleSql) {
//        buildConnection();
//        Object obj = null;
//        try {
//            pre = conn.prepareStatement(singleSql);
//            rs = pre.executeQuery();
//            if (rs.next()) {
//                obj = rs.getObject(1);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return obj;
//    }

    /**
     * 执行可变参数语句，返回单个对象
     *
     * @param singleSql SQL 语句
     * @param params 可变长度的参数
     * @return 结果对象
     */
    public static Object executeSingleQuery(String singleSql, Object... params) {
        buildConnection();
        Object obj = null;
        try {
            pre = conn.prepareStatement(singleSql);
            int count = 1;
            for (Object param : params) {
                if (param instanceof String) {
                    pre.setString(count++, param.toString());
                } else if (param instanceof Integer) {
                    pre.setInt(count++, new Integer(param.toString()));
                } else if (param instanceof Double) {
                    pre.setDouble(count++, new Double(param.toString()));
                } else if (param instanceof Float) {
                    pre.setFloat(count++, new Float(param.toString()));
                } else {
                    pre.setObject(count++, param);
                }
            }
            rs = pre.executeQuery();
            if (rs.next()) {
                obj = rs.getObject(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return obj;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("数据库连接已关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
