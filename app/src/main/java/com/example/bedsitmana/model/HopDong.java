package com.example.bedsitmana.model;

public class HopDong {
    private int maHopDong;
    private int giaTien;
    private int thoiHan;
    private int soNguoi;
    private int soXe;
    private int maPhong;
    private String maNguoiThue;

    public HopDong() {
    }

    public HopDong(int maHopDong, int giaTien, int thoiHan, int soNguoi, int soXe, int maPhong, String maNguoiThue) {
        this.maHopDong = maHopDong;
        this.giaTien = giaTien;
        this.thoiHan = thoiHan;
        this.soNguoi = soNguoi;
        this.soXe = soXe;
        this.maPhong = maPhong;
        this.maNguoiThue = maNguoiThue;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(int thoiHan) {
        this.thoiHan = thoiHan;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public int getSoXe() {
        return soXe;
    }

    public void setSoXe(int soXe) {
        this.soXe = soXe;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaNguoiThue() {
        return maNguoiThue;
    }

    public void setMaNguoiThue(String maNguoiThue) {
        this.maNguoiThue = maNguoiThue;
    }
}
