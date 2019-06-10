package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TinKMDAO;

import Model.TinKM;

/**
 * Servlet implementation class LoadHinhTinKM
 */
@WebServlet(urlPatterns = { "/LoadHinhTinKM" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class LoadHinhTinKM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TinKMDAO kmDAO;

	public void init() {

		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
//		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		kmDAO = new TinKMDAO(jdbcURL);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String maTKM = "";
		maTKM = request.getParameter("maTKM");

		TinKM tin = null;
		try {
			tin = kmDAO.getTinKM(maTKM);
//			System.out.println(tin.getMaTKM());
			Blob fileData = tin.getHinhBlob();
			InputStream is = null;
			is = fileData.getBinaryStream();

			byte[] bytes = null;

			bytes = new byte[(int) fileData.length()];

			bytes = fileData.getBytes(1, (int) fileData.length());

			response.resetBuffer();
			response.reset();
			response.setContentType("image/*");

			int bytesRead = 0;
			while ((bytesRead = is.read(bytes)) != -1) {
				response.getOutputStream().write(bytes, 0, bytesRead);
			}
			is.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
