package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HoaDonDAO;

import Model.HoaDon;

import java.util.List;

@WebServlet(name = "HoaDon", urlPatterns = { "/HoaDonServlet", "/HoaDonServlet/delete" })
public class HoaDonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HoaDonDAO hdDAO;

	public HoaDonServlet() {
		super();
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		

		hdDAO = new HoaDonDAO(jdbcURL);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		try {
			switch (path) {
			/*
			 * case "/HoaDonServlet": listHD(request, response); break; case
			 * "/HoaDonServlet/insert": try { insertHD(request, response); } catch
			 * (SQLException e) { e.printStackTrace(); } break;
			 */
			case "/HoaDonServlet/delete":
				deleteHD(request, response);
				break;
			/*
			 * case "/HoaDonServlet/edit": try { editHD(request, response); } catch
			 * (SQLException e) { e.printStackTrace(); } break;
			 */
			default:
				listHD(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// load danh sach hoa don
	private void listHD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<HoaDon> listHD;
		try {
			listHD = hdDAO.listAllHD();
			request.setAttribute("listHD", listHD);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminQLHD.jsp");
		dispatcher.forward(request, response);
	}

	// them hoa don
	private void insertHD(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HoaDon hd = new HoaDon();
		hd.setMaHD(request.getParameter("maHD"));

		hdDAO.insertHD(hd);

		response.sendRedirect("/HoaDonServlet");

	}

	private void editHD(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

//		String maHD =request.getParameter("maHD");
//		String tenLoai=request.getParameter("tenLoaiSP");
//		LoaiSP loaisp = new LoaiSP( maLoai,  tenLoai);
//
//		
//		try {			
//			loaiSPDAO.updateLoaiSP(loaisp);
//		
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//		request.setAttribute("sua",loaisp);
//		
//		String url = request.getContextPath() + "/HoaDonServlet";
//        response.sendRedirect(url);  
	}

	// xoa hoa don
	private void deleteHD(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String maHD = request.getParameter("maHD");
		HoaDon hd = new HoaDon(maHD);
		try {
			hdDAO.deleteHD(hd);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		String url = request.getContextPath() + "/HoaDonServlet";
		response.sendRedirect(url);
	}

}
