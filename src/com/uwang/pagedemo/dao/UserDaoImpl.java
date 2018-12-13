package com.uwang.pagedemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uwang.pagedemo.entity.UserEntity;


public class UserDaoImpl {
	
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/test_delete";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "123456";
		
	public List<UserEntity> findUserPage(Integer thisPage, Integer number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<UserEntity> userList = new ArrayList<>();
		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			String sql = "SELECT id, name, age FROM `user` LIMIT ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, thisPage);
			preparedStatement.setInt(2, number);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserEntity user = new UserEntity();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setAge(resultSet.getInt("age"));
				userList.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 6) 清理环境 （注意：先开的后关，后开的先关）
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	// 查询数据总条数
	public int getTotalNumber() {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sql = "SELECT count(id) AS total FROM `user`";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("total");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 6) 清理环境 （注意：先开的后关，后开的先关）
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
}
