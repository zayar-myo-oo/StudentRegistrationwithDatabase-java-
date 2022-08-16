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
import persistant.dao.UserDao;
import persistant.dto.StudentReq;
import persistant.dto.UserReq;

/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Student stu = new Student();
		stu.setStuId(request.getParameter("upid"));//STU1
		stu.setName(request.getParameter("upname"));
		stu.setDob(request.getParameter("updob"));
		stu.setGender(request.getParameter("upgender"));
		stu.setPhnumber(request.getParameter("upphone"));
		stu.setEducation(request.getParameter("upselect"));
		stu.setAttend(request.getParameterValues("upCourse"));

		StudentReq req = new StudentReq();
		req.setStuId(stu.getStuId());//STU1
		req.setName(stu.getName());
		req.setDob(stu.getDob());
		req.setGender(stu.getGender());
		req.setPhnumber(stu.getPhnumber());
		req.setEducation(stu.getEducation());
		req.setAttend(stu.getAttend());
		StudentDao stuupdate = new StudentDao();
		CourseDao coudao = new CourseDao();
		int i = stuupdate.updateUser(req);
		coudao.updateCourseDetail(req);
		if (i == 0) {
			System.out.println("Update something wrong");
			request.getRequestDispatcher("STU002.jsp").forward(request, response);
		}
		request.getRequestDispatcher("DisplayStudentServlet").forward(request, response);

	}

}
