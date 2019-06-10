package Model;

import java.io.InputStream;

import java.sql.Blob;

public class TinKM {
	private String maTKM;
	private String tieuDe;
	private String noiDung;
	//private String hinhAnh;
	private Blob hinhBlob;
	private InputStream hinhInput;
	
	
	public String getMaTKM() {
		return maTKM;
	}
	public void setMaTKM(String maTKM) {
		this.maTKM = maTKM;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	
	
	
	public TinKM(String maTKM) {
		super();
		this.maTKM = maTKM;
	}
	public TinKM(String maTKM, String tieuDe, String noiDung, Blob hinhBlob, InputStream hinhInput) {
		super();
		this.maTKM = maTKM;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.hinhBlob = hinhBlob;
		this.hinhInput = hinhInput;
	}
	public TinKM() {
	
	}
	
	public Blob getHinhBlob() {
		return hinhBlob;
	}

	public void setHinhBlob(Blob hinhBlob) {
		this.hinhBlob = hinhBlob;
	}

	public InputStream getHinhInput() {
		return hinhInput;
	}

	public void setHinhInput(InputStream hinhInput) {
		this.hinhInput = hinhInput;
	}

	

}
