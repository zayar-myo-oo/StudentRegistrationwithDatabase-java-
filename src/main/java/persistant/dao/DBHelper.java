package persistant.dao;

import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	//Singleton Design Pattern
	public class DBHelper {
			private final String DRIVER="com.mysql.cj.jdbc.Driver";
			private final String URL="jdbc:mysql://localhost:3306/studentregister";
			private final String USERNAME="root";
			private final String PASSWORD="zayar1832003";
			
			private static Connection con;
			private static DBHelper instance;
			
			public DBHelper() {
				try {
					Class.forName(DRIVER);//external jar
					
					 con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
					
				} catch (ClassNotFoundException e) {
					System.out.println("Driver not found");
					e.printStackTrace();
				} catch (SQLException e) {
				System.out.println(" Class not found");
					e.printStackTrace();
				}
			}

			public static DBHelper getInstance() {
				if(instance==null) {
					 instance=new DBHelper();
				}
				return instance;
			}
			
			public static Connection getConnection() {
				return con;
			}
			
			public static void closeConnection(Connection con,PreparedStatement stmt,ResultSet set) {
				try {
					con.close();
					stmt.close();
					set.close();
				} catch (SQLException e) {
					System.out.println("Connection error !!!");
					e.printStackTrace();
				}
			}
			public static void closeConnection(Connection con,PreparedStatement stmt) {
				try {
					con.close();
					stmt.close();
					
				} catch (SQLException e) {
					System.out.println("Connection error !!!");
					e.printStackTrace();
				}
			}
			
			public static void closeConnection(Connection con) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Connection error !!!");
					e.printStackTrace();
				}
			}
			
	}

