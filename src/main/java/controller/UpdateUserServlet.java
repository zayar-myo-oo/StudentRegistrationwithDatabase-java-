package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistant.dao.UserDao;
import persistant.dto.UserReq;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
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
			UserReq req=new UserReq();
			req.setUserId(request.getParameter("upid").substring(3));
			req.setUserName(request.getParameter("upname"));
			req.setEmail(request.getParameter("upemail"));
			req.setPassword(request.getParameter("uppassword"));
			req.setRole(request.getParameter("uprole"));
			
			UserDao dao=new UserDao();
			int i=dao.updateUser(req);
			if(i==0) {
				System.out.println("Update something wrong");
				request.getRequestDispatcher("USR002.jsp").forward(request, response);
			}
            request.getSession().setAttribute("userApp",dao.getAllUser());
			request.getRequestDispatcher("USR003.jsp").forward(request, response);
			
			
			
		
		
	}

}
