package persistant.dto;

import java.util.List;

import persistant.dao.StudentAttendCourse;

public class StudentRes {
	private String stuId;
	private String Name;
	private String dob;
	private String gender;
	private String phnumber;
	private String education;
	private List<StudentAttendCourse> attend;
//	private String photo;
	
	
	
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public List<StudentAttendCourse> getAttend() {
		return attend;
	}
	public void setAttend(List<StudentAttendCourse> list) {
		this.attend = list;
	}
}

