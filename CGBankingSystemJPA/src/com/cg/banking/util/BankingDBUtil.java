package com.cg.banking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankingDBUtil {
	private static Connection con=null;
	public static Connection getDBConnection() {
		if(con==null) {
			Properties bankingProperties=new Properties();
			try {
				bankingProperties.load(new FileInputStream(new File(".//resources//banking.properties")));
				String driver=bankingProperties.getProperty("driver");
				String url=bankingProperties.getProperty("url");
				String user=bankingProperties.getProperty("user");
				String password=bankingProperties.getProperty("password");

				//1st load driver
				Class.forName(driver);
				//2nd open connection
				con=DriverManager.getConnection(url,user,password);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	public static int getpinNumber() {
		return (int)(Math.random()*10000);
	}
}