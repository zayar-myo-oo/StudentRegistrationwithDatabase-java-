package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import persistant.dao.CourseDao;
import persistant.dao.StudentDao;
import persistant.dto.StudentReq;

/**
 * Servlet implementation class CreateStudentServlet
 */
@WebServlet("/CreateStudentServlet")
public class CreateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student stu=new Student();
		stu.setStuId(request.getParameter("stuId"));
		stu.setName(request.getParameter("stuName"));
		stu.setDob(request.getParameter("stuDOB"));
		stu.setGender(request.getParameter("stuGender"));
		stu.setPhnumber(request.getParameter("stuPhone"));
		stu.setEducation(request.getParameter("stuEdu"));
		stu.setAttend(request.getParameterValues("stuCourse"));
		StudentReq stureq=new StudentReq();
		stureq.setStuId(stu.getStuId());
		stureq.setName(stu.getName());
		stureq.setDob(stu.getDob());
		stureq.setGender(stu.getGender());
		stureq.setPhnumber(stu.getPhnumber());
		stureq.setEducation(stu.getEducation());
		stureq.setAttend(stu.getAttend());//CU01 CU02
		
		StudentDao studao=new StudentDao();
		CourseDao coudao=new CourseDao();
		int i=studao.InsertStu(stureq);
		coudao.InsertCourseDetail(stureq);
		
		if(i==1) {
		
			request.getRequestDispatcher("STU001.jsp").forward(request, response);
			
		}else {
			request.setAttribute("error","Check your database");
			request.getRequestDispatcher("STU001.jsp").forward(request, response);
		}
		
		
		
//		SELECT studentid,courseid  FROM coursedetails
//		JOIN courses on coursedetails.id=courses.id
//		JOIN students on coursedetails.id=students.id;
		
	}

}
