package com.example.bedsitmana.model;

public class PhongTro {
    private int maPhong,maCS;
    private String tenPhong,tienNghi;
    private int trangThai;

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaCS() {
        return maCS;
    }

    public void setMaCS(int maCS) {
        this.maCS = maCS;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTienNghi() {
        return tienNghi;
    }

    public void setTienNghi(String tienNghi) {
        this.tienNghi = tienNghi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public PhongTro(int maPhong, int maCS, String tenPhong, String tienNghi, int trangThai) {
        this.maPhong = maPhong;
        this.maCS = maCS;
        this.tenPhong = tenPhong;
        this.tienNghi = tienNghi;
        this.trangThai = trangThai;
    }

    public PhongTro() {
    }
}
