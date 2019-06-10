package Model;

public class NhanVien {

	private String maNV;
	private String hoTen;
	private String gioiTinh;
	private String diaChi;
	private String email;
	private String sdt;
	private String tenDN;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	
	public NhanVien() {
		super();
	}
	
	
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	public NhanVien(String maNV,String hoTen,String gioiTinh,String diaChi,String email,String sdt,String tenDN)
	{
		super();
		this.maNV=maNV;
		this.hoTen=hoTen;
		this.gioiTinh=gioiTinh;
		this.diaChi=diaChi;
		this.email=email;
		this.sdt=sdt;
		this.tenDN=tenDN;
	}
	
	
}
