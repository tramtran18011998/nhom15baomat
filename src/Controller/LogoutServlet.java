package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.accountDAO;
import Model.ACCOUNT;

@WebServlet("/LogoutServlet")

public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private accountDAO accDAO;
	public LogoutServlet() {
		super();
	}


	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		accDAO = new accountDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/TrangChu.jsp");
*/		response.sendRedirect(request.getContextPath() + "/TrangChu.jsp?tenDN=null");
		
	
		// Redirect to Home Page.
		//response.sendRedirect("TrangChu.jsp");
		
		
	}


}
