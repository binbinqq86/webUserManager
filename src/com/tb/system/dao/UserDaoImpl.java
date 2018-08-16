package com.tb.system.dao;

import com.tb.system.bean.User;
import com.tb.system.utils.CloseUtil;
import com.tb.system.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tb
 * @time 2018/8/14 下午5:08
 * @des 用户操作相关
 */
public class UserDaoImpl implements IDao<User> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public static UserDaoImpl getInstance() {
        return SingletonInstance.instance;
    }

    private static class SingletonInstance {
        private static final UserDaoImpl instance = new UserDaoImpl();
    }

    private UserDaoImpl() {
        connection = JdbcUtil.getConnection();
        String sql = "create table if not exists user" +
                "(userId int(10) auto_increment primary key," +
                "userName varchar(20) not null," +
                "nickName varchar(20) null," +
                "password varchar(20) not null," +
                "role int(5) default '0' not null," +
                "constraint userName unique (userName)" +
                ") DEFAULT CHARSET=utf8mb4";//支持emoji表情
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }

        List<User> list=read(null);
        if(list!=null&&list.size()>0){
            for (User u:list) {
                if(u.userName.equals("admin")){
                    return;
                }
            }
        }
        //添加一个默认的管理员
        List<User> l=new ArrayList<>();
        User u=new User();
        u.userName="admin";
        u.nickName="admin";
        u.role=-1;
        u.password="admin";
        l.add(u);
        create(l);
    }

    @Override
    public int[] create(List<User> list) {
        String sql = "insert into user(userName,nickName,password,role) values(?,?,?,?)";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (User user : list) {
                preparedStatement.setString(1, user.userName);
                preparedStatement.setString(2, user.nickName);
                preparedStatement.setString(3, user.password);
                preparedStatement.setInt(4, user.role);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public List<User> read(Object o) {
        List<User> list = new ArrayList<>();
        ResultSet resultSet;
        String sql = "select * from user where userName=?";
        if (o == null) {
            sql = "select * from user";
        }
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            if (o != null) {
                preparedStatement.setString(1, String.valueOf(o));
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.userName = resultSet.getString("userName");
                user.nickName = resultSet.getString("nickName");
                user.password = resultSet.getString("password");
                user.userId = resultSet.getInt("userId");
                user.role = resultSet.getInt("role");
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public int[] update(List<User> list) {
        String sql = "update user set nickName=?,password=?,role=? where userName=?";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (User user : list) {
                preparedStatement.setString(1, user.nickName);
                preparedStatement.setString(2, user.password);
                preparedStatement.setInt(3, user.role);
                preparedStatement.setString(4, user.userName);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public int[] delete(List<Object> list) {
        String sql = "delete from user where userName=?";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (Object o : list) {
                preparedStatement.setString(1, String.valueOf(o));
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }
}
