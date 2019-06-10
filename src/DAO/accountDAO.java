package DAO;

import Model.ACCOUNT;
import Model.NhanVien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class accountDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public accountDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public void connect() throws SQLException {
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

	public boolean insertACC(ACCOUNT acc) throws SQLException {
		String sql = "exec spr_ACCOUNT_ThemACCOUNT ?,?,?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, acc.getTenDN());
		statement.setString(2, acc.getmK());
		statement.setString(3, acc.getQuyenHan());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public boolean deleteACC(ACCOUNT acc) throws SQLException {
		String sql = "exec spro_ACCOUNT_XoaACCOUNT ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, acc.getTenDN());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateACC(ACCOUNT acc) throws SQLException {
		String sql = "exec spro_ACCOUNT_SuaACCOUNT ?,?,?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, acc.getTenDN());
		statement.setString(2, acc.getmK());
		statement.setString(3, acc.getQuyenHan());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

//	check ten dn da ton tai hay chua
	public boolean CheckTenDN(String tenDN) throws SQLException {

		connect();
		String sql = "select * from ACCOUNT where TenDN ='" + tenDN + "'";

		PreparedStatement statement;

		try {
			statement = jdbcConnection.prepareCall(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				disconnect();
				return true;
			}
		} catch (SQLException e) {
			Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
	}

	public ACCOUNT Login(String username, String password) throws SQLException {
		ACCOUNT ac=null;
		
		String sql = "select * from ACCOUNT where TenDN='" + username + "' and MK='" + password + "'";
		
		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			 ac = new ACCOUNT();
			ac.setTenDN(resultSet.getString("TenDN"));
			ac.setmK(resultSet.getString("MK"));
			
			ac.setQuyenHan(resultSet.getString("QuyenHan"));
			
		}
		
		
		resultSet.close();
		statement.close();
		disconnect();
		return ac;
		
		
	}
	
}