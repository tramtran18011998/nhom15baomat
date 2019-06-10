package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.KhachHang;
import Model.NhanVien;

public class KhachHangDAO {

	private String jdbcURL;
    
    private Connection jdbcConnection;
     
    public KhachHangDAO(String jdbcURL) {
        this.jdbcURL = jdbcURL;
        
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
            	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL);
           
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
     
    public List<KhachHang> listAllKH() throws SQLException {
        List<KhachHang> listKH = new ArrayList<>();
         
        String sql = "SELECT * FROM KHACHHANG";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String maKH = resultSet.getString("maKH");
            String tenDN=resultSet.getString("TenDN");
            String hoTen = resultSet.getString("HoTen");
            
            String diaChi=resultSet.getString("DiaChi");
            String email=resultSet.getString("Email");
            String sdt=resultSet.getString("SDT");
            int tichLuy=resultSet.getInt("TichLuy");
            KhachHang kh = new KhachHang(maKH,tenDN,hoTen,diaChi,email,sdt,tichLuy);
            listKH.add(kh);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listKH;
    }
    
    
    public boolean insertKH(KhachHang kh) throws SQLException {
        String sql = "exec spro_KhachHang_ThemKH ?,?,?,?,?,?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, kh.getMaKH());
        statement.setString(2, kh.getHoTen());
        statement.setString(3, kh.getDiaChi());
        statement.setString(4, kh.getEmail());
        statement.setString(5, kh.getSdt());
        statement.setString(6, kh.getTenDN());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public boolean deleteKH(KhachHang kh) throws SQLException {
        String sql = "exec spro_KhachHang_XoaKhachHang ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,kh.getMaKH());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateKH(KhachHang kh) throws SQLException {
        String sql = "UPDATE KHACHHANG SET  TenDN= ?,HoTen = ?, DiaChi = ?, Email=?, SDT=?,TichLuy = ?";
        sql += " WHERE MaKH = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        
        statement.setString(1,kh.getMaKH() );
        statement.setString(2, kh.getTenDN());
        statement.setString(3, kh.getHoTen());
        statement.setString(4, kh.getDiaChi());
        statement.setString(5, kh.getEmail());
        statement.setString(6, kh.getSdt());
        statement.setInt(7, kh.getTichLuy());
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public KhachHang getKhachHang(String MaKH) throws SQLException {
    	KhachHang kh = null;
        String sql = "SELECT * FROM KHACHHANG WHERE MaKH = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, MaKH);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	kh=new KhachHang();
        	kh.setMaKH(resultSet.getString("MaKH"));
        	kh.setTenDN(resultSet.getString("TenDN"));
        	kh.setHoTen(resultSet.getString("HoTen"));
        	kh.setDiaChi(resultSet.getString("DiaChi"));
        	kh.setEmail(resultSet.getString("Email"));
        	kh.setSdt(resultSet.getString("SDT"));
        	kh.setTichLuy(resultSet.getInt("TichLuy"));
        	
        }
         
        resultSet.close();
        statement.close();
         
        return kh;
    }
    public KhachHang getKhachHangTenDN(String MaKH) throws SQLException {
    	KhachHang kh = null;
        String sql = "SELECT * FROM KHACHHANG WHERE TenDN = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, MaKH);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	kh=new KhachHang();
        	kh.setMaKH(resultSet.getString("MaKH"));
        	kh.setTenDN(resultSet.getString("TenDN"));
        	kh.setHoTen(resultSet.getString("HoTen"));
        	kh.setDiaChi(resultSet.getString("DiaChi"));
        	kh.setEmail(resultSet.getString("Email"));
        	kh.setSdt(resultSet.getString("SDT"));
        	kh.setTichLuy(resultSet.getInt("TichLuy"));
        	
        }
         
        resultSet.close();
        statement.close();
         
        return kh;
    }
    
    public String maKhachHangCaoNhat() {

		String re = "";
		try {
			connect();
			String sqlExec = "EXEC KHACHHANG_LayMaKHCaoI";

			PreparedStatement statement = jdbcConnection.prepareStatement(sqlExec);

			statement.setEscapeProcessing(true);
			statement.setQueryTimeout(15);

			ResultSet res = statement.executeQuery();

			res.next();
			String temp = res.getString(1);

			// temp = "SP###"
			// Xu ly chuoi
			temp = temp.substring(2, temp.length());
			int so = Integer.parseInt(temp) + 1;
			temp = Integer.toString(so);
			if (temp.length() == 1)
				re = "KH00" + temp;
			else if (temp.length() == 2)
				re = "KH0" + temp;
			else
				re = "KH" + temp;

			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}

    
}
