package com.web.db.core;

import java.sql.*;

public class JdbcTemplate {

	/**
	 * 執行查詢操作
	 * 
	 * @param pscreator       創建語句對象
	 * @param callbackHandler 結果集合處理對象
	 * @throws DataAccessException
	 */
	public void query(PreparedStatementCreator pscreator, RowCallbackHandler callbackHandler)
			throws DataAccessException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBHelp.getConnection();
			preparedStatement = pscreator.createPreparedStatement(connection);
			resultSet = preparedStatement.executeQuery();

			// 遍歷結果集合
			while (resultSet.next()) {
				callbackHandler.processRow(resultSet);
			}

		} catch (SQLException e) {
			throw new DataAccessException("JdbcTemplate中的SQLException", e);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("JdbcTemplate中的ClassNotFoundException", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DataAccessException("JdbcTemplate中不能中斷數據庫連接", e);
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DataAccessException("JdbcTemplate中不能釋放語句對象", e);
				}
			}

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DataAccessException("JdbcTemplate中無法關閉結果集合對象", e);
				}
			}
		}

	}

	/**
	 * 數據修改操作
	 * 
	 * @param pscreator 創建語句對象
	 * @throws DataAccessException
	 */
	public void update(PreparedStatementCreator pscreator) throws DataAccessException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBHelp.getConnection();
			preparedStatement = pscreator.createPreparedStatement(connection);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException("JdbcTemplate中的SQLException", e);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("JdbcTemplate中的ClassNotFoundException", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DataAccessException("JdbcTemplate中的SQLException", e);
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DataAccessException("JdbcTemplate中的SQLException", e);
				}
			}
		}
	}
}
