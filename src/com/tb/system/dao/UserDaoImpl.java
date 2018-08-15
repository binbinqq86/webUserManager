package com.tb.system.dao;

import com.tb.system.bean.User;
import com.tb.system.utils.CloseUtil;

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

    @Override
    public int[] create(List<User> list) {
        connection = GetConnection.getConnection();
        String sql = "insert into user(userName,nickName,password,role) values(?,?,?,?)";
        try {
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
        connection = GetConnection.getConnection();
        ResultSet resultSet;
        String sql = "select * from user where userName=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(o));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.userName = o.toString();
                user.nickName = resultSet.getString("nickName");
                user.password = resultSet.getString("password");
                user.userId = resultSet.getInt("userId");
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
        connection = GetConnection.getConnection();
        String sql = "update user set nickName=?,password=?,role=? where userName=?";
        try {
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
        connection = GetConnection.getConnection();
        String sql = "delete from user where userName=?";
        try {
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
