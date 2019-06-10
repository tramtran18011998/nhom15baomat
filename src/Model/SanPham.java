package Model;

import java.io.InputStream;

import java.sql.Blob;

public class SanPham {
	private String maSP;
	private String tenSP;
	private int soLuong;
	private int giaBanDau;
	private int giaBan;
	private int khuyenMai;
	private Blob hinhBlob;//Download
	private InputStream hinhInput;//Upload
	private String maLoai;

	public SanPham() {
		super();
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	public SanPham(String maSP, String tenSP, int soLuong, int giaBanDau, int giaBan, int khuyenMai, Blob hinhBlob,
			InputStream hinhInput, String maLoai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.giaBanDau = giaBanDau;
		this.giaBan = giaBan;
		this.khuyenMai = khuyenMai;
		this.hinhBlob = hinhBlob;
		this.hinhInput = hinhInput;
		this.maLoai = maLoai;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getGiaBanDau() {
		return giaBanDau;
	}

	public void setGiaBanDau(int giaBanDau) {
		this.giaBanDau = giaBanDau;
	}

	public int getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}

	public int getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
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

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

}
