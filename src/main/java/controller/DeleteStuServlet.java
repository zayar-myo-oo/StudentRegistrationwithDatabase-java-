package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistant.dao.CourseDao;
import persistant.dao.StudentAttendCourse;
import persistant.dao.StudentDao;
import persistant.dto.StudentReq;

/**
 * Servlet implementation class DeleteStuServlet
 */
@WebServlet("/DeleteStuServlet")
public class DeleteStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("userId").substring(3);
		StudentReq dto=new StudentReq();
		StudentAttendCourse att=new StudentAttendCourse();
		dto.setStuId(id);
		att.setStudentid(id);
		StudentDao dao=new StudentDao();
		CourseDao dao1=new CourseDao(); 
		dao.deleteUser(id);
		int i=dao1.deleteCouserDetail(att);
		if (i == 0) {
			System.out.println("Update something wrong");
			request.getRequestDispatcher("DisplayStudentServlet").forward(request, response);
		}
		request.getRequestDispatcher("DisplayStudentServlet").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
