package persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import persistant.dto.CourseReq;
import persistant.dto.CourseRes;
import persistant.dto.StudentReq;

public class CourseDao {

	public Integer InsertCourse(CourseReq coureq) {
		int result=0;
		Connection con=DBHelper.getInstance().getConnection();
		
		try {
			PreparedStatement stmt=con.prepareStatement("INSERT INTO courses(id,name)VALUES(?,?);");
			stmt.setInt(1, Integer.parseInt(coureq.getId().substring(3)));//COU1
			stmt.setString(2, coureq.getName());			
			result=stmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println("Insert Course error");
			e.printStackTrace();
		}
		return result;
		 
	}
	
	public List<CourseRes> getAllCourse(){
		List<CourseRes> cou=new ArrayList<CourseRes>();
		Connection con=DBHelper.getInstance().getConnection();
		
		PreparedStatement stmt=null;
		try {
		stmt=con.prepareStatement("SELECT * FROM courses;");
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) {
		CourseRes res=new CourseRes();
			res.setId("COU"+String.valueOf(rs.getInt("id")));
			res.setName(rs.getString("name"));
			cou.add(res);
		}
		}catch(SQLException e) {
			System.out.println("Getall course error");
			e.printStackTrace();
		}
		
		return cou;
	}
	
	
	public String getmaxId() {
		String maxId=null;
		Connection con=DBHelper.getInstance().getConnection();
		String sql="SELECT MAX(id) max FROM courses;";
		try {
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet resultSet=stmt.executeQuery();
			
			while(resultSet.next()) {
				maxId="COU"+String.valueOf(resultSet.getInt("max"));//9
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return maxId ==null ? "COU": maxId;	
	}
	
	public Integer InsertCourseDetail(StudentReq stureq) {
		int result=0;
		Connection con=DBHelper.getInstance().getConnection();
		try {
			for(String stu:stureq.getAttend()) {
				PreparedStatement stmt=con.prepareStatement("INSERT INTO coursedetails(studentid,courseid)VALUES(?,?);");
				stmt.setInt(1, Integer.parseInt(stureq.getStuId().substring(3)));//STU1			
				stmt.setInt(2,Integer.parseInt(stu.substring(3)));//COU1,2,3			
				result=stmt.executeUpdate();				
			}
			
		} catch (SQLException e) {
			System.out.println("Insert Course error");
			e.printStackTrace();
		}
		return result;
		 
	}
	
	
	public int updateCourseDetail(StudentReq req) {
		int result=0;
		Connection con=DBHelper.getInstance().getConnection();
		String sql="UPDATE coursedetails SET courseid=? WHERE studentid=?;";
		try {
		for(String stu:req.getAttend() ) {
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setInt(1,Integer.parseInt(stu.substring(3)));//COU1,2,3
				stmt.setInt(2, Integer.parseInt(req.getStuId().substring(3)));//STU1
				result=stmt.executeUpdate();
		}
			} catch (SQLException e) {
				System.out.println("Update courseDetails error");
				e.printStackTrace();
			}		
		return result;
	}
	
	public int deleteCouserDetail(StudentAttendCourse dto) {
		int result=0;
		String sql="DELETE FROM coursedetails WHERE studentid=?;";
		Connection con=DBHelper.getInstance().getConnection();
		try {
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,Integer.parseInt(dto.getStudentid()));//1
		result=ps.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("database error");
		}
		
		return result;
	}
	
	
}
