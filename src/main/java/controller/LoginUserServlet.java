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
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserServlet() {
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
        user.setUserId(request.getParameter("lguserid"));
        user.setPassword(request.getParameter("lgpassword"));

        if(user.getUserId().equals("") || user.getPassword().equals("")) {
            request.setAttribute("error","not found !!");
            request.getRequestDispatcher("LGN001.jsp").include(request, response);
        }
        
        else {
//not null so yin req ko add mal
            UserReq req = new UserReq();
            req.setUserId(user.getUserId().substring(3));
            req.setPassword(user.getPassword());
            UserDao dao = new UserDao();
            UserRes res = dao.selectOne(req);

            if(res == null) {
                request.setAttribute("error","Check you data again !!");
                request.getRequestDispatcher("LGN001.jsp").include(request, response);
            }else {
            	UserDao userdao = new UserDao();

                request.getSession().setAttribute("userApp",userdao.getAllUser());
                request.getSession().setAttribute("userLName", res);
                request.getRequestDispatcher("MNU001.jsp").include(request, response);
            }	
	}
        
	}
}
