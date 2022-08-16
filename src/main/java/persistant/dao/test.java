package persistant.dao;

import java.sql.Connection;

public class test {
	public static void main(String [] args) {
		Connection con=new DBHelper().getInstance().getConnection();
		if(con != null) {
			System.out.println("Ok tal");
		}else {
			System.out.println("Error");
		}
	}

}
