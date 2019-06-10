package Controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import Model.*;
import DAO.*;

@WebServlet(name = "LoaiSP", urlPatterns = { "/LoaiSPServlet", "/LoaiSPServlet/insert", "/LoaiSPServlet/edit",
		"/LoaiSPServlet/update", "/LoaiSPServlet/delete" })

public class LoaiSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoaiSPDAO loaiSPDAO;

	public LoaiSPServlet() {
		super();
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");

		loaiSPDAO = new LoaiSPDAO(jdbcURL);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		try {

			System.out.println(path);
			switch (path) {
			case "/LoaiSPServlet/insert":
				insertLoaiSP(request, response);
				break;
			case "/LoaiSPServlet/update":
				updateLoaiSP(request, response);
				break;
			case "/LoaiSPServlet/edit":
				editLoaiSP(request, response);
				break;
			case "/LoaiSPServlet/delete":
				deleteLoaiSP(request, response);
				break;
			default:
				listLoaiSP(request, response);
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

	private void listLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		List<LoaiSP> listLoaiSP;
		String maLoaiMoi = "";
		try {
			maLoaiMoi = loaiSPDAO.maxMaLoai();

			listLoaiSP = loaiSPDAO.listAllLoaiSP();
			request.setAttribute("listLoaiSP", listLoaiSP);
			request.setAttribute("maLoaiMoi", maLoaiMoi);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminLoaiSP.jsp");
		dispatcher.forward(request, response);
	}

	private void insertLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		LoaiSP lSP = new LoaiSP();
		lSP.setMaLoai(request.getParameter("txtMaLoai"));
		lSP.setTenLoaiSP(request.getParameter("txtTenLoaiSP"));

		loaiSPDAO.insertLoaiSP(lSP);

		response.sendRedirect("/LoaiSPServlet");

	}

	private void editLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

//		String maLoai=request.getParameter("maLoai");
//		String tenLoai=request.getParameter("tenLoaiSP");
		/*
		 * LoaiSP loaisp = new LoaiSP();
		 * loaisp.setMaLoai(request.getParameter("txtMaLoai"));
		 * loaisp.setTenLoaiSP(request.getParameter("txtTenLoaiSP"));
		 * 
		 * try { loaiSPDAO.updateLoaiSP(loaisp);
		 * 
		 * } catch (SQLException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * request.setAttribute("sua", loaisp);
		 * 
		 * String url = request.getContextPath() + "/LoaiSPServlet";
		 * response.sendRedirect(url);
		 */

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String maLoai = request.getParameter("maLoai");
		LoaiSP lSP = new LoaiSP();
		lSP = loaiSPDAO.getLoaiSP(maLoai);
		request.setAttribute("loaiSanPham", lSP);
		request.getRequestDispatcher("/LoaiSPServlet").forward(request, response);

	}

	public void updateLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		LoaiSP lSP = new LoaiSP();

		lSP.setMaLoai(request.getParameter("txtMaLoai"));
		lSP.setTenLoaiSP(request.getParameter("txtTenLoaiSP"));
		loaiSPDAO.updateLoaiSP(lSP);
		response.sendRedirect("/LoaiSPServlet");
	}

	private void deleteLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String maLoai = request.getParameter("maLoai");
		LoaiSP loaisp = new LoaiSP(maLoai);
		try {
			loaiSPDAO.deleteLoaiSP(loaisp);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect("/LoaiSPServlet");

		/*
		 * String url = request.getContextPath() + "/LoaiSPServlet";
		 * response.sendRedirect(url);
		 */
	}

}
