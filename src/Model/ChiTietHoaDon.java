package Model;

public class ChiTietHoaDon {
	
	private String maHD;
	private String maSP;
	private int soLuong;
	private int gia;
	private int thanhTien;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public int getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(String maHD, String maSP, int soLuong, int gia, int thanhTien) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.gia = gia;
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	public ChiTietHoaDon(String maHD, String maSP) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
	}
	
}
