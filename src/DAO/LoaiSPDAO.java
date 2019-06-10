package DAO;

import Model.LoaiSP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoaiSPDAO {

	private String jdbcURL;
	private Connection jdbcConnection;

	public LoaiSPDAO(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

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

	// load danh sach loai san pham
	public List<LoaiSP> listAllLoaiSP() throws SQLException {
		List<LoaiSP> listLoaiSP = new ArrayList<>();

		String sql = "SELECT * FROM LOAISP";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			LoaiSP lSP = new LoaiSP();
			lSP.setMaLoai(resultSet.getString("MaLoai"));
			lSP.setTenLoaiSP(resultSet.getString("TenLoaiSP"));
			listLoaiSP.add(lSP);

			/*
			 * String maLoai = resultSet.getString("MaLoai"); String tenLoaiSP =
			 * resultSet.getString("TenLoaiSP");
			 * 
			 * LoaiSP loaisp = new LoaiSP(maLoai, tenLoaiSP); listLoaiSP.add(loaisp);
			 */
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listLoaiSP;
	}

	// lay ma loai cao nhat
	public String maxMaLoai() {
		String ma = "";
		try {
			connect();
			String sqlExec = "SELECT TOP 1(MaLoai) FROM LOAISP order by MaLoai DESC";

			PreparedStatement statement = jdbcConnection.prepareStatement(sqlExec);

			statement.setEscapeProcessing(true);
			statement.setQueryTimeout(15);

			ResultSet res = statement.executeQuery();

			res.next();
			String temp = res.getString(1);

			temp = temp.substring(2, temp.length());
			int so = Integer.parseInt(temp) + 1;
			temp = Integer.toString(so);
			if (temp.length() == 1)
				ma = "L00" + temp;
			else if (temp.length() == 2)
				ma = "L0" + temp;
			else
				ma = "L" + temp;

			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ma;
	}

	// them loai san pham
	public boolean insertLoaiSP(LoaiSP lSP) throws SQLException {
		String sql = "INSERT INTO LOAISP (MaLoai,TenLoaiSP) VALUES (?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, lSP.getMaLoai());
		statement.setString(2, lSP.getTenLoaiSP());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	// sua loai san pham
	public boolean updateLoaiSP(LoaiSP lSP) throws SQLException {
		String sql = "UPDATE LOAISP SET TenLoaiSP = ?";
		sql += " WHERE MaLoai = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, lSP.getTenLoaiSP());
		statement.setString(2, lSP.getMaLoai());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	// xoa loai san pham
	public boolean deleteLoaiSP(LoaiSP lSP) throws SQLException {
		String sql = "DELETE FROM LOAISP where MaLoai = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, lSP.getMaLoai());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	// tim kiem loai san pham theo ma loai
	public LoaiSP getLoaiSP(String MaLoai) throws SQLException {
		LoaiSP lSP = null;
		String sql = "SELECT * FROM LOAISP WHERE MaLoai = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, MaLoai);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			lSP = new LoaiSP();
			lSP.setMaLoai(resultSet.getString("MaLoai"));
			lSP.setTenLoaiSP(resultSet.getString("TenLoaiSP"));

			/*
			 * String TenLoaiSP = resultSet.getString("TenLoaiSP");
			 * 
			 * loaisp = new LoaiSP(MaLoai, TenLoaiSP);
			 */
		}

		resultSet.close();
		statement.close();

		return lSP;
	}
}
