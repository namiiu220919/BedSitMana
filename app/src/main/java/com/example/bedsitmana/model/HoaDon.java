package com.example.bedsitmana.model;

public class HoaDon {
    private int maHoaDon;
    private int soDien;
    private int soNuoc;
    private int phiDichVu;
    private int trangThai;
    private int maPhong;
    private String maNguoiThue;
    private String maKeToan;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int soDien, int soNuoc, int phiDichVu, int trangThai, int maPhong, String maNguoiThue, String maKeToan) {
        this.maHoaDon = maHoaDon;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.phiDichVu = phiDichVu;
        this.trangThai = trangThai;
        this.maPhong = maPhong;
        this.maNguoiThue = maNguoiThue;
        this.maKeToan = maKeToan;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoDien() {
        return soDien;
    }

    public void setSoDien(int soDien) {
        this.soDien = soDien;
    }

    public int getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        this.soNuoc = soNuoc;
    }

    public int getPhiDichVu() {
        return phiDichVu;
    }

    public void setPhiDichVu(int phiDichVu) {
        this.phiDichVu = phiDichVu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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

    public String getMaKeToan() {
        return maKeToan;
    }

    public void setMaKeToan(String maKeToan) {
        this.maKeToan = maKeToan;
    }
}
