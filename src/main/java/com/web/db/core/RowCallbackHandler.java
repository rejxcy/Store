package com.web.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �B�z���G���X
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
