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

import DAO.LoaiSPDAO;
import DAO.SanPhamDAO;
import Model.LoaiSP;
import Model.SanPham;

@WebServlet(name = "SanPham", urlPatterns = { "/SanPhamServlet", "/SanPhamServlet/insert", "/SanPhamServlet/edit",
		"/SanPhamServlet/update", "/SanPhamServlet/delete", "/ThucDon" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class SanPhamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private SanPhamDAO SPDAO;
	private LoaiSPDAO LOAISPDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		SPDAO = new SanPhamDAO(jdbcURL);
		LOAISPDAO = new LoaiSPDAO(jdbcURL);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		try {

			System.out.println(path);
			switch (path) {
			case "/SanPhamServlet/insert":
				insertSanPham(request, response);
				break;
			case "/SanPhamServlet/update":
				updateSanPham(request, response);
				break;
			case "/SanPhamServlet/edit":
				editSanPham(request, response);
				break;
			case "/SanPhamServlet/delete":
				deleteSanPham(request, response);
				break;
			case "/ThucDon":
				showSanPham(request, response);
				break;
			default:
				listSanPham(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void deleteSanPham(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		String maSP = (request.getParameter("maSP"));

		SPDAO.deleteLoaiSP(maSP);
		response.sendRedirect("/SanPhamServlet");

	}

	private void updateSanPham(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		SanPham sp = new SanPham();

		sp.setMaSP(request.getParameter("txtMaSP").toString());
		sp.setTenSP(request.getParameter("txtTenSP").toString());
		sp.setSoLuong(Integer.parseInt(request.getParameter("txtSoLuong")));
		sp.setGiaBanDau(Integer.parseInt(request.getParameter("txtGiaBanDau")));
		sp.setGiaBan(Integer.parseInt(request.getParameter("txtGiaBan")));
		sp.setKhuyenMai(Integer.parseInt(request.getParameter("txtKhuyenMai")));
		int i = 0;
		try {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && fileName.length() > 0) {
					InputStream is = part.getInputStream();
					if (i == 0) {
						sp.setHinhInput(is);
						i++;
					} else if (i == 1) {
						sp.setHinhInput(is);
						i++;
					}
				}
			}
		} catch (ServletException e1) {
			e1.printStackTrace();
		}

		sp.setMaLoai(request.getParameter("selectMaLoai"));

		SPDAO.updateSP(sp);
		response.sendRedirect("/SanPhamServlet");

	}

	private void editSanPham(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String maSP = request.getParameter("maSP");

		SanPham sp = new SanPham();
		sp = SPDAO.getSP(maSP);

		request.setAttribute("sanPham", sp);

		request.getRequestDispatcher("/SanPhamServlet").forward(request, response);

	}

	private void insertSanPham(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		SanPham sp = new SanPham();

		sp.setMaSP(request.getParameter("txtMaSP").toString());
		sp.setTenSP(request.getParameter("txtTenSP").toString());
		sp.setSoLuong(Integer.parseInt(request.getParameter("txtSoLuong")));
		sp.setGiaBanDau(Integer.parseInt(request.getParameter("txtGiaBanDau")));
		sp.setGiaBan(Integer.parseInt(request.getParameter("txtGiaBan")));
		sp.setKhuyenMai(Integer.parseInt(request.getParameter("txtKhuyenMai")));
		try {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && fileName.length() > 0) {
					InputStream is = part.getInputStream();
					sp.setHinhInput(is);
				}
			}
		} catch (ServletException e1) {
			e1.printStackTrace();
		}

		sp.setMaLoai(request.getParameter("selectMaLoai"));

		SPDAO.insertSP(sp);
		response.sendRedirect("/SanPhamServlet");

	}

	private String extractFileName(Part part) {
		// form-data; name="file"; filename="C:\file1.zip"
		// form-data; name="file"; filename="C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// C:\file1.zip
				// C:\Note\file2.zip
				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				// file1.zip
				// file2.zip
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

	private void listSanPham(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {

		// Tiếng Việt hoạt động
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		List<SanPham> listSP;
		List<LoaiSP> listLoaiSP;

		// Phục vụ chức năng search , sort, xem theo loại sản phẩm
		String search = "*";
		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		String sort = "*";
		if (request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		String loaiSP = "*";
		if (request.getParameter("selectLoaiSP") != null) {
			loaiSP = request.getParameter("selectLoaiSP");
		}
		String maSPNew = "";
		try {

			listSP = SPDAO.listAllPhanTrang(sort, search, loaiSP);
			listLoaiSP = LOAISPDAO.listAllLoaiSP();
			maSPNew = SPDAO.maSanphamCaoNhat();
			request.setAttribute("listSP", listSP);
			request.setAttribute("listLoaiSP", listLoaiSP);
			request.setAttribute("search", search);
			request.setAttribute("sort", sort);
			request.setAttribute("selectLoaiSP", loaiSP);
			request.setAttribute("maSPNew", maSPNew);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminQLSP.jsp");
		dispatcher.forward(request, response);
	}

	//show san pham tren trang client
	
	/*private void showSanPham(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {

		// Tiếng Việt hoạt động
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		List<SanPham> listSP;
		List<LoaiSP> listLoaiSP;

		// Phục vụ chức năng search , sort, xem theo loại sản phẩm
		String search = "*";
		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		String sort = "*";
		if (request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		String loaiSP = "*";
		if (request.getParameter("selectLoaiSP") != null) {
			loaiSP = request.getParameter("selectLoaiSP");
		}
		
		try {

			listSP = SPDAO.listAllPhanTrang(sort, search, loaiSP);
			listLoaiSP = LOAISPDAO.listAllLoaiSP();			
			request.setAttribute("listSP", listSP);
			request.setAttribute("listLoaiSP", listLoaiSP);
			request.setAttribute("search", search);
			request.setAttribute("sort", sort);
			request.setAttribute("selectLoaiSP", loaiSP);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ThucDon.jsp");
		dispatcher.forward(request, response);
	}*/
	private void showSanPham(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {

		// Tiếng Việt hoạt động
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		List<SanPham> listSP;
		List<LoaiSP> listLoaiSP;

		// Phục vụ chức năng search , sort, xem theo loại sản phẩm
		String search = "*";
		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		String sort = "*";
		if (request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		String loaiSP = "*";
		if (request.getParameter("selectLoaiSP") != null) {
			loaiSP = request.getParameter("selectLoaiSP");
		}
		
		try {

			listSP = SPDAO.listAllPhanTrang(sort, search, loaiSP);
			listLoaiSP = LOAISPDAO.listAllLoaiSP();			
			request.setAttribute("listSP", listSP);
			request.setAttribute("listLoaiSP", listLoaiSP);
			request.setAttribute("search", search);
			request.setAttribute("sort", sort);
			request.setAttribute("selectLoaiSP", loaiSP);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ThucDon.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
