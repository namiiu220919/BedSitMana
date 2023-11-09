package com.example.bedsitmana.model;

public class NguoiThue {
    private String maNguoithue,matKhauNT,tenNguoiThue,sdt;
    private int cCCD,namSinh;
    private String gioiTinh;

    public String getMaNguoithue() {
        return maNguoithue;
    }

    public void setMaNguoithue(String maNguoithue) {
        this.maNguoithue = maNguoithue;
    }

    public String getMatKhauNT() {
        return matKhauNT;
    }

    public void setMatKhauNT(String matKhauNT) {
        this.matKhauNT = matKhauNT;
    }

    public String getTenNguoiThue() {
        return tenNguoiThue;
    }

    public void setTenNguoiThue(String tenNguoiThue) {
        this.tenNguoiThue = tenNguoiThue;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getcCCD() {
        return cCCD;
    }

    public void setcCCD(int cCCD) {
        this.cCCD = cCCD;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public NguoiThue(String maNguoithue, String matKhauNT, String tenNguoiThue, String sdt, int cCCD, int namSinh, String gioiTinh) {
        this.maNguoithue = maNguoithue;
        this.matKhauNT = matKhauNT;
        this.tenNguoiThue = tenNguoiThue;
        this.sdt = sdt;
        this.cCCD = cCCD;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
    }

    public NguoiThue() {
    }
}
