package DAO;

import Model.TinKM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TinKMDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

//    public TinKMDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
//        this.jdbcURL = jdbcURL;
//        this.jdbcUsername = jdbcUsername;
//        this.jdbcPassword = jdbcPassword;
//    }

	public TinKMDAO(String jdbcURL) {
		this.jdbcURL = jdbcURL;
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

	public boolean insertKM(TinKM km) throws SQLException {
		String sql = "exec spro_TinKM_ThemTinKM ?,?,?,?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, km.getMaTKM());
		statement.setString(2, km.getTieuDe());
		statement.setString(3, km.getNoiDung());
		statement.setBlob(4, km.getHinhInput());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		// disconnect();
		return rowInserted;
	}

	public List<TinKM> listAllKM() throws SQLException {
		List<TinKM> listTKM = new ArrayList<>();

		String sql = "SELECT * FROM TIN_KM";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			TinKM tin = new TinKM();
			tin.setMaTKM(resultSet.getString("MaTKM"));
			tin.setTieuDe(resultSet.getString("TieuDe"));
			tin.setNoiDung(resultSet.getString("NoiDung"));
			tin.setHinhBlob(resultSet.getBlob("HinhAnh"));

			listTKM.add(tin);

		}

		resultSet.close();
		statement.close();

		disconnect();

		return listTKM;
	}

	public String maTinKMCaoNhat() {

		String re = "";
		try {
			connect();
			String sqlExec = "EXEC TINKM_LayMaTinKMCaoI";

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
				re = "KM00" + temp;
			else if (temp.length() == 2)
				re = "KM0" + temp;
			else
				re = "KM" + temp;

			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}

	public boolean deleteKM(String maTKM) throws SQLException {
		String sql = "exec spro_TinKM_XoaTinKM  ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, maTKM);

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateTinKM(TinKM km) throws SQLException {
		String sql = "UPDATE TIN_KM SET MaTKM = ?, TieuDe = ?, NoiDung = ?, HinhAnh =?";

		sql += " WHERE MaTKM = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, km.getMaTKM());
		statement.setString(2, km.getTieuDe());
		statement.setString(3, km.getNoiDung());
		statement.setBlob(4, km.getHinhInput());
		statement.setString(5, km.getMaTKM());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public TinKM getTinKM(String MaTKM) throws SQLException {

		TinKM tin = new TinKM();
		String sql = "SELECT * FROM TIN_KM WHERE MaTKM = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, MaTKM);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			tin.setMaTKM(resultSet.getString("MaTKM"));
			tin.setTieuDe(resultSet.getString("TieuDe"));
			tin.setNoiDung(resultSet.getString("NoiDung"));
			tin.setHinhBlob(resultSet.getBlob("HinhAnh"));

		}

		resultSet.close();
		statement.close();

		return tin;
	}
}
