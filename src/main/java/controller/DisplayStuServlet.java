package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistant.dao.StudentDao;
import persistant.dto.StudentRes;

/**
 * Servlet implementation class DisplayStuServlet
 */
@WebServlet("/DisplayStuServlet")
public class DisplayStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayStuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id=request.getParameter("id");
		String name=request.getParameter("name");
		String course=request.getParameter("course");
		if(id.isBlank() && name.isBlank() && course.isBlank()) {
			StudentDao dao=new StudentDao();
			List<StudentRes> res= dao.getAllUser();
			request.getSession().setAttribute("stuApp", res);
			request.getRequestDispatcher("STU003.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
