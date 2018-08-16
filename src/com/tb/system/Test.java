package com.tb.system;

import com.tb.system.bean.User;
import com.tb.system.dao.UserDaoImpl;
import com.tb.system.utils.JdbcUtil;
import com.tb.system.utils.CloseUtil;

import java.sql.*;
import java.util.List;

public class Test {

    public static void main(String[] args) {
//        Connection conn = JdbcUtil.getConnection();
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            if (conn != null) {
//                System.out.println("数据库连接成功。。。");
//                statement = conn.createStatement();
//                String str = "select * from user";
//                resultSet = statement.executeQuery(str);
//                System.out.println("id     userName     password");
//                while (resultSet.next()) {
//                    System.out.print(resultSet.getString(1));
//                    System.out.print("     ");
//                    System.out.print(resultSet.getString(2));
//                    System.out.print("     ");
//                    System.out.println(resultSet.getString(4));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            CloseUtil.closeQuietly(conn, statement, resultSet);
//        }
        List<User> list = UserDaoImpl.getInstance().read(null);
        for (User u : list) {
            System.out.println(u.toString());
        }
    }
}
