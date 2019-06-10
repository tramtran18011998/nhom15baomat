package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CT_HoaDonDAO;
import DAO.HoaDonDAO;

import Model.Cart;
import Model.ChiTietHoaDon;
import Model.HoaDon;
import Model.Item;


@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HoaDonDAO hdDAO;
	private CT_HoaDonDAO cthdDAO;

	public CheckOutServlet() {
		super();

	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		hdDAO = new HoaDonDAO(jdbcURL);
		cthdDAO=new CT_HoaDonDAO(jdbcURL);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
//		Date date=new Date();
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		String maHDNew = "";

		Cart cart=(Cart) session.getAttribute("cart");
		try {
			HoaDon hd=new HoaDon();
			maHDNew=hdDAO.maxMaHD();
			hd.setMaHD(maHDNew);		
		    
			hd.setNgayLap(sqlDate);
			hd.setTongTien(cart.totalCart());
			hdDAO.insertHD(hd);
			int thanhtien=0; 
			for(Map.Entry<String, Item> list:cart.getCartItems().entrySet())
			{
				thanhtien=list.getValue().getQuantity()*list.getValue().getProduct().getGiaBan();
				cthdDAO.insertCTHD(new ChiTietHoaDon(maHDNew,list.getValue().getProduct().getMaSP(),list.getValue().getQuantity(),list.getValue().getProduct().getGiaBan(),thanhtien));
			}
			//hdDAO.insertHD(hd);
			cart =new Cart();
			session.setAttribute("cart", cart);
			
		} catch (Exception e) {
			
		}
		cart =new Cart();
		session.setAttribute("cart", cart);
		response.sendRedirect("/TrangChu.jsp");
	}

}
