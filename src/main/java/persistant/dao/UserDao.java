package persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistant.dto.UserReq;
import persistant.dto.UserRes;

public class UserDao {

	public Integer InsertUser(UserReq user) {
		DBHelper.getInstance();
		Connection con=DBHelper.getConnection();
		int result=0;
		try {
			PreparedStatement stmt=con.prepareStatement("INSERT INTO users(name,email,password,role)VALUES(?,?,?,?);");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getRole());
			result=stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Insert User error");
			e.printStackTrace();
		}
		return result;
		 
	}
	public List<UserRes> getAllUser() {
		List<UserRes> users=new ArrayList<>();
		String query="SELECT * FROM users;";
		
		PreparedStatement stmt=null;
		Connection con=DBHelper.getInstance().getConnection();
		try {
			 stmt=con.prepareStatement(query);
			ResultSet set=stmt.executeQuery();
			 while(set.next()) {
				 UserRes userres=new UserRes();
				 userres.setUserId("USR"+String.valueOf(set.getInt("id")));
				 userres.setUserName(set.getString("name"));
				 userres.setEmail(set.getString("email"));
				 userres.setPassword(set.getString("password"));
				 userres.setRole(set.getString("role"));
				 users.add(userres);
			 }
//			 DBHelper.closeConnection(con, stmt, set);
		} catch (SQLException e) {
				System.out.println("getAllUser error");
			e.printStackTrace();
		}
		
		return users;
	}
	
	public boolean deleteUser(String string) {
		boolean bol=false;
		PreparedStatement stmt=null;
		Connection con=DBHelper.getInstance().getConnection();
		String query="DELETE FROM users WHERE id=?";
		try {
			stmt=con.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(string));
			int result=stmt.executeUpdate();
			if(result==1) {
				bol=true;
			}
		} catch (SQLException e) {
			System.out.println("Delete User error");
			e.printStackTrace();
		}
//		DBHelper.closeConnection(con, stmt);
		return bol;
	}
	
	public UserRes selectOne(UserReq dto)
	{
		Connection con=DBHelper.getInstance().getConnection();
		UserRes res=null;
		String sql="SELECT * FROM users WHERE id=? AND password=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(dto.getUserId()));
			ps.setString(2,dto.getPassword());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				res=new UserRes();
				res.setUserId("USR"+rs.getString("id"));
				res.setUserName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));			
			}
		}
		catch (SQLException e) 
		{
			System.out.println("SELECT ONE ERROR");
			e.printStackTrace();
		}
		return res;
	}
	
	public int  updateUser(UserReq dto)
	{
		Connection con=DBHelper.getInstance().getConnection();
		int result=0;		
		String sql="UPDATE users SET name=?,email=?,password=?,role=? WHERE id=?;";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString (1,dto.getUserName());
			ps.setString (2,dto.getEmail());
			ps.setString (3,dto.getPassword());
			ps.setString (4,dto.getRole());
			ps.setInt (5,Integer.parseInt(dto.getUserId()));
			result=ps.executeUpdate();
			if(result==0) {
				System.out.println("No Update Error");
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Update error");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<UserRes> specificUser(UserReq req) {
		Connection con=DBHelper.getInstance().getConnection();
        UserRes res = null;
        List<UserRes> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE id =? or name =?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(req.getUserId()));
            ps.setString(2,req.getUserName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            res = new UserRes();
            res.setUserId("USR"+String.valueOf(rs.getInt("id")));
            res.setUserName(rs.getString("name"));
            res.setEmail(rs.getString("email"));
            res.setPassword(rs.getString("password"));
            res.setRole(rs.getString("role"));
            list.add(res);

        }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

}
