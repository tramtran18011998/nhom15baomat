package Model;


public class ACCOUNT {
	
	private String tenDN;
	
	private String mK;
	
	private String quyenHan;

	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getmK() {
		return mK;
	}
	public void setmK(String mK) {
		this.mK = mK;
	}
	public String getQuyenHan() {
		return quyenHan;
	}
	public void setQuyenHan(String quyenHan) {
		this.quyenHan = quyenHan;
	}
	
	public ACCOUNT() {
		super();
	}
	
	
	public ACCOUNT(String tenDN) {
		super();
		this.tenDN = tenDN;
	}
	public ACCOUNT(String tenDN, String mK, String quyenHan) {
		super();
		this.tenDN = tenDN;
		this.mK = mK;
		this.quyenHan = quyenHan;
	}
	
	
}
