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
import javafx.scene.control.Alert;
import Model.KhachHang;
import DAO.KhachHangDAO;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private accountDAO accDAO;
	private KhachHangDAO khDAO;

	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		accDAO = new accountDAO(jdbcURL, jdbcUsername, jdbcPassword);
		khDAO= new KhachHangDAO(jdbcURL);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ACCOUNT acc = new ACCOUNT();
		String tenDN=request.getParameter("tenDN");
		String mK=request.getParameter("mK");
		String tendnKH=null;
		String maKH=request.getParameter("maKH");
		
		HttpSession session = request.getSession();
		
		try {
			acc = accDAO.Login(request.getParameter("tenDN"), request.getParameter("mK"));
		
			KhachHang khachhang=new KhachHang();
			
			if (acc != null && acc.getQuyenHan().equals("Admin")) {
				/*
				*/
				
				
				session.setAttribute("acc", acc);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/SanPhamServlet");
				dispatcher.forward(request, response);
			}
			if (acc != null && acc.getQuyenHan().equals("User")) {
				session.setAttribute("tenDN", tenDN);
				session.setAttribute("mK", mK);
		
				
//				tendnKH=(String) session.getAttribute("tenDN");
//				khachhang.setTenDN(tendnKH);		
//				khachhang=(KhachHang)session.getAttribute("khachhang");
//				request.setAttribute("khachhang", khachhang);
//				khachhang.setMaKH(maKH);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/TrangChu.jsp");
				dispatcher.forward(request, response);
			}
			if (acc == null) {
				response.sendRedirect(request.getContextPath()+"/Login.jsp?error=0");
			}
			
		} catch (

		SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}

		/*
		 * RequestDispatcher dispatcher =
		 * getServletContext().getRequestDispatcher("/TrangChu.jsp");
		 */

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

}
