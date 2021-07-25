package com.web.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 處理結果集合
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
