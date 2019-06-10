package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.ChiTietHoaDon;


public class CT_HoaDonDAO {
	private String jdbcURL;

    private Connection jdbcConnection;
     
    public CT_HoaDonDAO(String jdbcURL) {
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
    
    public boolean insertCTHD(ChiTietHoaDon cthd) throws SQLException {
        String sql = "INSERT INTO CT_HOADON (MaHD,MaSP,SoLuong,Gia,ThanhTien) VALUES (?,?,?,?,?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        
        statement.setString(1, cthd.getMaHD());
        statement.setString(2, cthd.getMaSP());
        statement.setInt(3, cthd.getSoLuong());
        statement.setInt(4, cthd.getGia());
        statement.setInt(5,cthd.getThanhTien());
        
        
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

}
