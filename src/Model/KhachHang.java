package Model;

public class KhachHang {

	private String maKH;
	private String tenDN;
	private String hoTen;
	private String diaChi;
	private String email;
	private String sdt;
	private int tichLuy;
	
	public KhachHang() {
		super();
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenDN() {
		return tenDN;
	}

	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public int getTichLuy() {
		return tichLuy;
	}

	public void setTichLuy(int tichLuy) {
		this.tichLuy = tichLuy;
	}

	
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenDN, String hoTen, String diaChi, String email, String sdt, int tichLuy) {
		super();
		this.maKH = maKH;
		this.tenDN = tenDN;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.email = email;
		this.sdt = sdt;
		this.tichLuy = tichLuy;
	}
	
	
	
}
