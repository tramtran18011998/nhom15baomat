package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.SanPham;

public class SanPhamDAO {
	private String jdbcURL;
	private Connection jdbcConnection;

	public SanPhamDAO(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

	public SanPhamDAO()
	{}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	// hàm l?y danh sách s?n ph?m có phân trang
	public List<SanPham> listAllPhanTrang(String sort, String search, String loaiSP)
			throws SQLException, ClassNotFoundException {
		connect();
		List<SanPham> listSP = new ArrayList<>();

		String sqlExec = "EXEC SP_LaySP ?,?,?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sqlExec);

		statement.setString(1, sort);
		statement.setString(2, search);
		statement.setString(3, loaiSP);

		statement.setEscapeProcessing(true);
		statement.setQueryTimeout(15);

		ResultSet res = statement.executeQuery();

		while (res.next()) {
			SanPham SP = new SanPham();

			SP.setMaSP(res.getString("MaSP"));
			SP.setTenSP(res.getString("TenSP"));
			SP.setSoLuong(res.getInt("SoLuong"));
			SP.setGiaBanDau(res.getInt("GiaBanDau"));
			SP.setGiaBan(res.getInt("GiaBan"));
			SP.setKhuyenMai(res.getInt("TTKM"));
			SP.setHinhBlob(res.getBlob("HinhSP"));
			SP.setMaLoai(res.getString("MaLoai"));

			listSP.add(SP);
		}
		return listSP;
	}

	public boolean insertSP(SanPham SP) throws SQLException {

		String sql = "INSERT INTO SANPHAM (MaSP,TenSP,SoLuong,GiaBanDau,GiaBan,TTKM,HinhSP,MaLoai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, SP.getMaSP());
		statement.setString(2, SP.getTenSP());
		statement.setInt(3, SP.getSoLuong());
		statement.setInt(4, SP.getGiaBanDau());
		statement.setInt(5, SP.getGiaBan());
		statement.setInt(6, SP.getKhuyenMai());
		statement.setBlob(7, SP.getHinhInput());
		statement.setString(8, SP.getMaLoai());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();

		return rowInserted;
	}

	public List<SanPham> listAllSP() throws SQLException {
		List<SanPham> listSP = new ArrayList<>();

		String sql = "SELECT * FROM SANPHAM";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			SanPham SP = new SanPham();
			SP.setMaSP(resultSet.getString("MaSP"));
			SP.setTenSP(resultSet.getString("TenSP"));
			SP.setSoLuong(resultSet.getInt("SoLuong"));
			SP.setGiaBanDau(resultSet.getInt("GiaBanDau"));
			SP.setGiaBan(resultSet.getInt("GiaBan"));
			SP.setKhuyenMai(resultSet.getInt("TTKM"));
			SP.setHinhBlob(resultSet.getBlob("HinhSP"));
			SP.setMaLoai(resultSet.getString("MaLoai"));
			listSP.add(SP);
		}

		resultSet.close();
		statement.close();

		return listSP;
	}

	// hàm tr? v? s? dòng
	public int getSoDong(String loaiSP, String search) throws SQLException {
		connect();

		String sqlExec = "EXEC SP_LaySPCount ?,?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sqlExec);
		statement.setString(1, loaiSP);
		statement.setString(2, search);

		statement.setEscapeProcessing(true);
		statement.setQueryTimeout(15);

		ResultSet res = statement.executeQuery();
		disconnect();

		return res.getInt(1);
	}

	// l?y mã SP cao nh?t, tách chu?i
	//
	public String maSanphamCaoNhat() {

		String re = "";
		try {
			connect();
			String sqlExec = "EXEC SP_LayMaSPCaoI";

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
				re = "SP00" + temp;
			else if (temp.length() == 2)
				re = "SP0" + temp;
			else
				re = "SP" + temp;

			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}

	public boolean deleteLoaiSP(String maSP) throws SQLException {
		String sql = "DELETE FROM SANPHAM where MaSP = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, maSP);

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateSP(SanPham SP) throws SQLException {
		String sql = "UPDATE SANPHAM SET MaSP = ?, TenSP = ?, SoLuong = ?, Giabandau=?, GiaBan=?, TTKM =?, HinhSP =?, MaLoai = ?";

		sql += " WHERE MaSP = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, SP.getMaSP());
		statement.setString(2, SP.getTenSP());
		statement.setInt(3, SP.getSoLuong());
		statement.setInt(4, SP.getGiaBanDau());
		statement.setInt(5, SP.getGiaBan());
		statement.setInt(6, SP.getKhuyenMai());
		statement.setBlob(7, SP.getHinhInput());
		statement.setString(8, SP.getMaLoai());
		statement.setString(9, SP.getMaSP());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public SanPham getSP(String MaSP) throws SQLException {
		SanPham SP = null;
		String sql = "SELECT * FROM SANPHAM WHERE MaSP = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, MaSP);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			SP = new SanPham();

			SP.setMaSP(resultSet.getString("MaSP"));
			SP.setTenSP(resultSet.getString("TenSP"));
			SP.setSoLuong(resultSet.getInt("SoLuong"));
			SP.setGiaBanDau(resultSet.getInt("GiaBanDau"));
			SP.setGiaBan(resultSet.getInt("GiaBan"));
			SP.setKhuyenMai(resultSet.getInt("TTKM"));
			SP.setHinhBlob(resultSet.getBlob("HinhSP"));
			SP.setMaLoai(resultSet.getString("MaLoai"));
		}

		resultSet.close();
		statement.close();

		return SP;
	}
}
