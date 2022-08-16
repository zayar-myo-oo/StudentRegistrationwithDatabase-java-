package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import persistant.dao.CourseDao;
import persistant.dto.CourseReq;
import persistant.dto.CourseRes;

/**
 * Servlet implementation class CouserRegister
 */
@WebServlet("/CouserRegister")
public class CouserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouserRegister() {
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
			String id=request.getParameter("courseId");
			String name=request.getParameter("courseName");
			Course cu=new Course();
			cu.setId(id);
			cu.setName(name);
			if(cu.getName().equals("") || cu.getId().equals("")) {
				request.setAttribute("errorCu", "Fill the blank");
				request.getRequestDispatcher("BUD003.jsp").forward(request, response);
			}
			
			CourseDao cudao=new CourseDao();
			List<CourseRes> daocu=cudao.getAllCourse();
			for(CourseRes cures:daocu){
				if(cures.getName().equals(cu.getName())) {
					request.setAttribute("errorCu", "Duplicate Course");
					request.getRequestDispatcher("BUD003.jsp").forward(request, response);
				}
			}
			
			CourseReq req=new CourseReq();
			req.setId(cu.getId());//COU1
			req.setName(cu.getName());
			
			cudao.InsertCourse(req);
			
			//request.getSession().setAttribute("count", "COU"+String.valueOf(Integer.parseInt(req.getId().substring(3))+1));
			
			request.getServletContext().setAttribute("course", daocu);//Stu
			request.getRequestDispatcher("BUD003.jsp").forward(request, response);
			
			

	}

}
