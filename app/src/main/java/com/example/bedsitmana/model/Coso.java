package com.example.bedsitmana.model;

public class Coso {
    private int maCoso;
    private String tenCoso;
    private String diaChi;
    private int phiDichVu;
    private int giaDien;
    private int giaNuoc;

    public Coso() {
    }

    public Coso(int maCoso, String tenCoso, String diaChi, int phiDichVu, int giaDien, int giaNuoc) {
        this.maCoso = maCoso;
        this.tenCoso = tenCoso;
        this.diaChi = diaChi;
        this.phiDichVu = phiDichVu;
        this.giaDien = giaDien;
        this.giaNuoc = giaNuoc;
    }

    public int getMaCoso() {
        return maCoso;
    }

    public void setMaCoso(int maCoso) {
        this.maCoso = maCoso;
    }

    public String getTenCoso() {
        return tenCoso;
    }

    public void setTenCoso(String tenCoso) {
        this.tenCoso = tenCoso;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getPhiDichVu() {
        return phiDichVu;
    }

    public void setPhiDichVu(int phiDichVu) {
        this.phiDichVu = phiDichVu;
    }

    public int getGiaDien() {
        return giaDien;
    }

    public void setGiaDien(int giaDien) {
        this.giaDien = giaDien;
    }

    public int getGiaNuoc() {
        return giaNuoc;
    }

    public void setGiaNuoc(int giaNuoc) {
        this.giaNuoc = giaNuoc;
    }

    @Override
    public String toString() {
        return "Coso{" +
                "maCoso=" + maCoso +
                ", tenCoso='" + tenCoso + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", phiDichVu=" + phiDichVu +
                ", giaDien=" + giaDien +
                ", giaNuoc=" + giaNuoc +
                '}';
    }
}
