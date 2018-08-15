package com.tb.system.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author tb
 * @time 2018/8/14 下午5:07
 * @des 获取数据库连接对象
 */
public class JdbcUtil {
    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    //数据库名称
    private static final String DB_NAME = "tb";
    //数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "tb123=root";

    /**
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            if(connection!=null){
                System.out.println("connection to the database is success......");
            }else{
                System.out.println("connection to the database is failure......");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
