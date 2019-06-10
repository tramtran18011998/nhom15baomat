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

import DAO.SanPhamDAO;
import Model.SanPham;

/**
 * @author IT 1006
 * @since 3:55PM 9/12/2018 LoadImage from Database for all Website
 */
@WebServlet(urlPatterns = { "/LoadHinh" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class LoadHinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SanPhamDAO SPDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");

		SPDAO = new SanPhamDAO(jdbcURL);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String maSP = "";
		maSP = request.getParameter("maSP");

		SanPham sp = null;
		try {
			sp = SPDAO.getSP(maSP);
			Blob fileData = sp.getHinhBlob();
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
