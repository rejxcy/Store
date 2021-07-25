package com.web.db.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelp {
	
	static Properties info = new Properties();
	
	static {
		//��o�ݩʤ�󪺿�J�y
		InputStream in = DBHelp.class.getClassLoader().getResourceAsStream("config.properties");
		
		try {
			//�q�ݩʤ��Ū�����ܶqinfo
			info.load(in);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

//    ���SQL�s��
//    private static String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&verifyServerCertificate=false&useSSL=false";
//    private static String user = "root";
//    private static String password = "cjxj8793";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName(info.getProperty("driver"));

        Connection connection = DriverManager.getConnection(info.getProperty("url"), info);
        return connection;
    }
}
