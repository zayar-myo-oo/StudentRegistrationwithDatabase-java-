package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistant.dao.UserDao;
import persistant.dto.UserReq;


/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
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
		User user = new User();
		user.setEmail( request.getParameter("email"));
		user.setPassword( request.getParameter("password"));
		user.setRole( request.getParameter("userRole"));
		user.setUserName( request.getParameter("name"));
			
		UserReq userreq=new UserReq();
		userreq.setEmail(user.getEmail());
		userreq.setPassword(user.getPassword());
		userreq.setRole(user.getRole());
		userreq.setUserName(user.getUserName());
		UserDao dao=new UserDao();
		 int i=dao.InsertUser(userreq);
		if(i==1) {
			request.getRequestDispatcher("USR001-01.jsp").forward(request, response);
		}else {
			request.setAttribute("error","Check your database");
			request.getRequestDispatcher("USR001-01.jsp").forward(request, response);
		}
					
			}
	}