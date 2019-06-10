package Model;

public class LoaiSP {
	
	private String maLoai;
    private String tenLoaiSP;
    
    
    public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoaiSP() {
		return tenLoaiSP;
	}
	public void setTenLoaiSP(String tenLoaiSP) {
		this.tenLoaiSP = tenLoaiSP;
	}

	public LoaiSP(String maLoai, String tenLoaiSP) {
		super();
		this.maLoai = maLoai;
		this.tenLoaiSP = tenLoaiSP;
	}
    
	public LoaiSP() {
		super();
	}
	public LoaiSP(String maLoai) {
		super();
		this.maLoai = maLoai;
	}
	

}
