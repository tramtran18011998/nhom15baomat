package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.TinKMDAO;
import Model.TinKM;

@WebServlet(name = "TinKM", urlPatterns = { "/TinKMServlet", "/TinKMServlet/insert", "/TinKMServlet/edit",
		"/TinKMServlet/delete", "/TinKMServlet/update", "/TinKMServlet/showKM","/TinKMServlet/showChiTietKM" })

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class TinKMServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TinKMDAO kmDAO;

	public TinKMServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");

		kmDAO = new TinKMDAO(jdbcURL);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		try {

			System.out.println(path);
			switch (path) {
			case "/TinKMServlet/insert":
				insertTinKM(request, response);
				break;
			case "/TinKMServlet/update":
				updateTinKM(request, response);
				break;
			case "/TinKMServlet/edit":
				editTinKM(request, response);
				break;
			case "/TinKMServlet/delete":
				deleteTinKM(request, response);
				break;
			case "/TinKMServlet/showKM":
				listTinKMTrangchu(request, response);
				break;
			case "/TinKMServlet/showChiTietKM":
				showChiTiet(request, response);
				break;
			default:
				listTinKM(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listTinKM(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TinKM> listTKM;
		String maTKMNew = "";
		try {
			listTKM = kmDAO.listAllKM();
			maTKMNew = kmDAO.maTinKMCaoNhat();
			request.setAttribute("listKM", listTKM);
			request.setAttribute("maTKMNew", maTKMNew);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminTinKM.jsp");
		dispatcher.forward(request, response);
	}

	private void listTinKMTrangchu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TinKM> listTKM;
		
		try {
			listTKM = kmDAO.listAllKM();
			
			request.setAttribute("listKM", listTKM);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/KhuyenMai.jsp");
		dispatcher.forward(request, response);
	}

	private void updateTinKM(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		TinKM tin = new TinKM();
		tin.setMaTKM(request.getParameter("txtMaTKM").toString());
		tin.setTieuDe(request.getParameter("txtTieuDe").toString());
		tin.setNoiDung(request.getParameter("txtNoiDung").toString());
		int i = 0;
		try {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && fileName.length() > 0) {
					InputStream is = part.getInputStream();
					if (i == 0) {
						tin.setHinhInput(is);
						i++;
					} else if (i == 1) {
						tin.setHinhInput(is);
						i++;
					}
				}
			}
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
		kmDAO.updateTinKM(tin);
		response.sendRedirect("/TinKMServlet");

	}

	private void editTinKM(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String maTKM = request.getParameter("maTKM");

		TinKM tin = new TinKM();
		tin = kmDAO.getTinKM(maTKM);

		request.setAttribute("tinKhuyenMai", tin);

		request.getRequestDispatcher("/TinKMServlet").forward(request, response);

	}
	
	private void showChiTiet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String maTKM = request.getParameter("maTKM");

		TinKM tin = new TinKM();
		tin = kmDAO.getTinKM(maTKM);

		request.setAttribute("tinKhuyenMai", tin);

		request.getRequestDispatcher("/TinKMServlet/showKM").forward(request, response);

	}

	private void insertTinKM(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		TinKM tin = new TinKM();
		tin.setMaTKM(request.getParameter("txtMaTKM").toString());
		tin.setTieuDe(request.getParameter("txtTieuDe").toString());
		tin.setNoiDung(request.getParameter("txtNoiDung").toString());

		try {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && fileName.length() > 0) {
					InputStream is = part.getInputStream();
					tin.setHinhInput(is);
				}
			}
		} catch (ServletException e1) {
			e1.printStackTrace();
		}

		kmDAO.insertKM(tin);
		response.sendRedirect("/TinKMServlet");

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

	private void deleteTinKM(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		String maTKM = (request.getParameter("maTKM"));

		kmDAO.deleteKM(maTKM);
		response.sendRedirect("/TinKMServlet");

	}

}
