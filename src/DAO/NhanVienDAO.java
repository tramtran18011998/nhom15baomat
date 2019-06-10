package DAO;

import Model.NhanVien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public NhanVienDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	/* lay ds nhan vien co phan trang */
//    public List<NhanVien> getAllPhanTrang(int dongDauTien, int dongCuoiCung, String sort, String search, String maNV)
//     
//			throws SQLException, ClassNotFoundException {
//		connect();
//		List<NhanVien> listNV = new ArrayList<>();
//
//		
//
//		
//
//		statement.setInt(1, dongDauTien);
//		statement.setInt(2, dongCuoiCung);
//		statement.setString(3, sort);
//		statement.setString(4, search);
//		statement.setString(5, maNV);
//
//		statement.setEscapeProcessing(true);
//		statement.setQueryTimeout(15);
//
//		ResultSet res = statement.executeQuery();
//
//		while (res.next()) {
//			NhanVien NV = new NhanVien();
//			NV.setHoTen(res.getString("hoten"));
//			NV.setGioiTinh(res.getString("GioiTinh"));
//			NV.setDiaChi(res.getString("DiaChi"));
//			NV.setEmail(res.getString("Email"));
//			NV.setSdt(res.getString("Sdt"));
//			NV.setTenDN(res.getString("TenDN"));
//			
//			
//			listNV.add(NV);
//		}
//		return listNV;
//	}

	public boolean insertNV(NhanVien nv) throws SQLException {
		String sql = "INSERT INTO NHANVIEN (MaNV,Hoten,GioiTinh,DiaChi,Email,SDT,TenDN) VALUES (?, ?,?,?,?,?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, nv.getMaNV());
		statement.setString(2, nv.getHoTen());
		statement.setString(3, nv.getGioiTinh());
		statement.setString(4, nv.getDiaChi());
		statement.setString(5, nv.getEmail());
		statement.setString(6, nv.getSdt());
		statement.setString(7, nv.getTenDN());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<NhanVien> listAllNV() throws SQLException {
		List<NhanVien> listNV = new ArrayList<>();

		String sql = "SELECT * FROM NHANVIEN";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String maNV = resultSet.getString("maNV");
			String hoTen = resultSet.getString("hoTen");
			String gioiTinh = resultSet.getString("gioiTinh");
			String diaChi = resultSet.getString("diaChi");
			String email = resultSet.getString("email");
			String sdt = resultSet.getString("Sdt");
			String tenDN = resultSet.getString("tenDN");
			NhanVien nv = new NhanVien(maNV, hoTen, gioiTinh, diaChi, email, sdt, tenDN);
			listNV.add(nv);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listNV;
	}

	public boolean deleteNV(NhanVien nv) throws SQLException {
		String sql = "DELETE FROM NHANVIEN where MaNV = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, nv.getMaNV());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateNV(NhanVien nv) throws SQLException {
		String sql = "UPDATE NHANVIEN SET MaNV = ?, HoTen = ?, GioiTinh = ?, DiaChi = ?, Email=?, SDT=?, TenDN= ?";
		sql += " WHERE MaNV = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, nv.getMaNV());
		statement.setString(2, nv.getHoTen());
		statement.setString(3, nv.getGioiTinh());
		statement.setString(4, nv.getDiaChi());
		statement.setString(5, nv.getEmail());
		statement.setString(6, nv.getSdt());
		statement.setString(7, nv.getTenDN());
		statement.setString(8, nv.getMaNV());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public String maNVCaoNhat() {

		String re = "";
		try {
			connect();
			String sqlExec = "EXEC SP_LayMaNVCaoI";

			PreparedStatement statement = jdbcConnection.prepareStatement(sqlExec);

			statement.setEscapeProcessing(true);
			statement.setQueryTimeout(15);

			ResultSet res = statement.executeQuery();

			res.next();
			String temp = res.getString(1);

			// temp = "NV###"
			// Xu ly chuoi
			temp = temp.substring(2, temp.length());
			int so = Integer.parseInt(temp) + 1;
			temp = Integer.toString(so);
			if (temp.length() == 1)
				re = "NV00" + temp;
			else if (temp.length() == 2)
				re = "NV0" + temp;
			else
				re = "NV" + temp;

			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}

	public NhanVien getNV(String MaNV) throws SQLException {
		NhanVien nv = null;
		String sql = "SELECT * FROM NHANVIEN WHERE MaNV = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, MaNV);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			nv = new NhanVien();
			nv.setMaNV(resultSet.getString("MaNV"));
			nv.setHoTen(resultSet.getString("HoTen"));
			nv.setGioiTinh(resultSet.getString("GioiTinh"));
			nv.setDiaChi(resultSet.getString("DiaChi"));
			nv.setEmail(resultSet.getString("Email"));
			nv.setSdt(resultSet.getString("SDT"));
			nv.setTenDN(resultSet.getString("tenDN"));
		}

		resultSet.close();
		statement.close();

		return nv;
	}
}
