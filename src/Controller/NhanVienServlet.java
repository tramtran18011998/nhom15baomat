package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.List;

import DAO.NhanVienDAO;
import DAO.accountDAO;
import Model.ACCOUNT;
import Model.NhanVien;

@WebServlet(name = "NhanVien", urlPatterns = { "/NhanVienServlet", "/NhanVienServlet/insert", "/NhanVienServlet/edit",
		"/NhanVienServlet/update", "/NhanVienServlet/delete" })

public class NhanVienServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private NhanVienDAO nvDAO;
	private accountDAO accDAO;

	public NhanVienServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		nvDAO = new NhanVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
		accDAO = new accountDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		try {
			System.out.println(path);
			switch (path) {

			case "/NhanVienServlet/insert":
				insertNhanVien(request, response);
				break;

			case "/NhanVienServlet/edit":
				editNhanVien(request, response);

				break;

			case "/NhanVienServlet/update":
				updateNhanVien(request, response);

				break;
			case "/NhanVienServlet/delete":
				deleteNV(request, response);
				break;
			default:
				listNhanVien(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insertNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// insertACC(request, response);
		ACCOUNT acc = new ACCOUNT();
		acc.setTenDN(request.getParameter("tenDN"));
		acc.setmK(request.getParameter("mK"));
		// acc.setQuyenHan("Admin");

		acc.setQuyenHan(request.getParameter("quyenHan"));
		accDAO.insertACC(acc);

		NhanVien nv = new NhanVien();
		nv.setMaNV(request.getParameter("maNV").toString());
		nv.setHoTen(request.getParameter("tenNV").toString());
		nv.setGioiTinh(request.getParameter("gioiTinh").toString());
		nv.setDiaChi(request.getParameter("diaChi").toString());
		nv.setEmail(request.getParameter("email").toString());
		nv.setSdt(request.getParameter("sdt").toString());
		nv.setTenDN(request.getParameter("tenDN").toString());

		nvDAO.insertNV(nv);
		System.out.println("loi");
		response.sendRedirect("/NhanVienServlet");

	}

	private void editNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String maNV = request.getParameter("maNV");
		NhanVien nv = new NhanVien();
		nv = nvDAO.getNV(maNV);

		request.setAttribute("nhanVien", nv);

		request.getRequestDispatcher("/NhanVienServlet").forward(request, response);

	}

	private void deleteNV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		NhanVien nv = new NhanVien();
		nv.setMaNV(request.getParameter("maNV"));

//		ACCOUNT acc=new ACCOUNT();
//		acc.setTenDN(request.getParameter("tenDN"));

		nvDAO.deleteNV(nv);
//		accDAO.deleteACC(acc);

		String url = request.getContextPath() + "/NhanVienServlet";
		response.sendRedirect(url);
	}

	private void updateNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		NhanVien nv = new NhanVien();

		nv.setMaNV(request.getParameter("maNV").toString());
		nv.setHoTen(request.getParameter("tenNV").toString());
		nv.setGioiTinh(request.getParameter("gioiTinh").toString());
		nv.setDiaChi(request.getParameter("diaChi").toString());
		nv.setEmail(request.getParameter("email").toString());
		nv.setSdt(request.getParameter("sdt").toString());
		nv.setTenDN(request.getParameter("tenDN").toString());

		nvDAO.updateNV(nv);
		response.sendRedirect("/NhanVienServlet");

	}

	private void listNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NhanVien> listNV;

		String maNVNew = "";
		try {
			maNVNew = nvDAO.maNVCaoNhat();
			listNV = nvDAO.listAllNV();
			request.setAttribute("listNV", listNV);
			request.setAttribute("maNVNew", maNVNew);
			System.out.println(maNVNew);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminQLNV.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

//	private void insertACC(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException {
//
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		ACCOUNT acc = new ACCOUNT();
//		acc.setTenDN(request.getParameter("tenDN"));
//		acc.setmK(request.getParameter("mK"));
//		acc.setQuyenHan("Admin");
//	
//		accDAO.insertACC(acc);
//
//		System.out.println("loi");
//		response.sendRedirect("/Version3/NhanVienServlet");
//
//	}
}
