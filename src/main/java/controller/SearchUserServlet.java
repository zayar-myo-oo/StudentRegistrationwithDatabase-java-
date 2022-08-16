	package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistant.dao.UserDao;
import persistant.dto.UserReq;
import persistant.dto.UserRes;

/**
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();

        if(request.getParameter("id").equals("")) {
            user.setUserId("USR0");
        }else {
            user.setUserId(request.getParameter("id"));
        }

        user.setUserName(request.getParameter("name"));
        
        UserReq req = new UserReq();
        req.setUserId(user.getUserId().substring(3));//USR
        req.setUserName(user.getUserName());


        UserDao dao = new UserDao();



        if(dao.specificUser(req).isEmpty()) {
            request.setAttribute("notfound","not found!!");
            request.getRequestDispatcher("USR003.jsp").include(request, response);
        }
        else {

            request.setAttribute("userApp",dao.specificUser(req));

            List<UserRes> a = dao.specificUser(req);


            request.getRequestDispatcher("USR003.jsp").include(request, response);
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
