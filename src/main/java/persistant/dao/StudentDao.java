package persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import persistant.dto.StudentReq;
import persistant.dto.StudentRes;
import persistant.dto.UserReq;
import persistant.dto.UserRes;


public class StudentDao {

	public Integer InsertStu(StudentReq stu) {
		DBHelper.getInstance();
		Connection con=DBHelper.getConnection();
		int result=0;
		try {
			PreparedStatement stmt=con.prepareStatement("INSERT INTO students(name,dob,gender,phone,education)VALUES(?,?,?,?,?);");
			stmt.setString(1, stu.getName());
			stmt.setString(2, stu.getDob());
			stmt.setString(3, stu.getGender());
			stmt.setString(4, stu.getPhnumber());
			stmt.setString(5, stu.getEducation());
			result=stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Insert User error");
			e.printStackTrace();
		}
		return result;
		 
	}
	
	public List<StudentRes> getAllUser() {
		List<StudentRes> users=new ArrayList<>();
		String query="SELECT * FROM students;";
		
		PreparedStatement stmt=null;
		Connection con=DBHelper.getInstance().getConnection();
		try {
			 stmt=con.prepareStatement(query);
			ResultSet set=stmt.executeQuery();
			 while(set.next()) {
				 StudentRes stures=new StudentRes();
				 stures.setStuId("STU"+String.valueOf(set.getInt("id")));
				 stures.setName(set.getString("name"));
				 stures.setDob(set.getString("dob"));
				 stures.setGender(set.getString("gender"));
				 stures.setPhnumber(set.getString("phone"));
				 stures.setEducation(set.getString("education"));
				 stures.setAttend(this.findAttendCoursesById(String.valueOf(set.getInt("id"))));
				 users.add(stures);
			 }
		} catch (SQLException e) {
				System.out.println("getAllUser error");
			e.printStackTrace();
		}
		
		return users;
	} 


public int  updateUser(StudentReq dto)
{
	Connection con=DBHelper.getInstance().getConnection();
	int result=0;		
	String sql="UPDATE students SET name=?,dob=?,gender=?,phone=?,education=? WHERE id=?;";
	try 
	{
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString (1,dto.getName());
		ps.setString (2,dto.getDob());
		ps.setString (3,dto.getGender());
		ps.setString (4,dto.getPhnumber());
		ps.setString(5, dto.getEducation());
		ps.setInt (6,Integer.parseInt(dto.getStuId().substring(3)));//1
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

public boolean deleteUser(String string) {
	boolean bol=false;
	PreparedStatement stmt=null;
	Connection con=DBHelper.getInstance().getConnection();
	String query="DELETE FROM students WHERE id=?";
	try {
		stmt=con.prepareStatement(query);
		stmt.setInt(1,Integer.parseInt(string));//1
		int result=stmt.executeUpdate();
		if(result==1) {
			bol=true;
		}
	} catch (SQLException e) {
		System.out.println("Delete User error");
		e.printStackTrace();
	}
//	DBHelper.closeConnection(con, stmt);
	return bol;
}


public List<UserRes> specificUser(UserReq req) {
	Connection con=DBHelper.getInstance().getConnection();
    UserRes res = null;
    List<UserRes> list = new ArrayList<>();
    String sql = "SELECT * FROM students WHERE id =? or name =?;";

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


public String getmaxId() {
	String maxId=null;
	Connection con=DBHelper.getInstance().getConnection();
	String sql="SELECT MAX(id) max FROM students;";
	try {
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet resultSet=stmt.executeQuery();
		
		while(resultSet.next()) {
			maxId="STU"+String.valueOf(resultSet.getInt("max"));//9
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
return maxId ==null ? "STU": maxId;	//STU9
}



public List<StudentAttendCourse> findAttendCoursesById( String studentId )
{
	Connection con=DBHelper.getInstance().getConnection();
	List<StudentAttendCourse> attendCourses = new ArrayList<>();
	String sql = "SELECT * FROM coursedetails atts JOIN courses crs ON atts.courseid = crs.id WHERE atts.studentid =?;";
	
	try {
		PreparedStatement pre = con.prepareStatement( sql );
		pre.setString( 1 , studentId); 
		ResultSet set = pre.executeQuery();
		
		while( set.next() )
		{
			StudentAttendCourse stuRes = new StudentAttendCourse();
			stuRes.setName(set.getString("name"));
			stuRes.setCourseid(set.getString("courseid"));
			stuRes.setStudentid(set.getString("studentid"));
			attendCourses.add(stuRes);
		}
		
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	
	
	return attendCourses;
}



}

