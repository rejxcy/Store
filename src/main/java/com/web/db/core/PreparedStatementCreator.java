package com.web.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 創建預先處理好的物件
 */
public interface PreparedStatementCreator {
    PreparedStatement createPreparedStatement(Connection conn) throws SQLException;
}
