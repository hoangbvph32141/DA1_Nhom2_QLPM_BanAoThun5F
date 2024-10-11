package com.n2.domainModel;

import java.util.Date;

public class HoaDon {
    
    private int ID;
    private int IDKH;
    private int IDNV;
    private int IDKM;
    private String MaHD;
    private Date ngayTao;
    private String lyDoNghi;
    private Date ngayThanhToan;
    private float donGiaSauGiam;
    private float tongTien;
    private int trangThai;

    public HoaDon() {
    }

    public HoaDon(int ID, int IDKH, int IDNV, int IDKM, String MaHD, Date ngayTao, String lyDoNghi, Date ngayThanhToan, float donGiaSauGiam, float tongTien, int trangThai) {
        this.ID = ID;
        this.IDKH = IDKH;
        this.IDNV = IDNV;
        this.IDKM = IDKM;
        this.MaHD = MaHD;
        this.ngayTao = ngayTao;
        this.lyDoNghi = lyDoNghi;
        this.ngayThanhToan = ngayThanhToan;
        this.donGiaSauGiam = donGiaSauGiam;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDKH() {
        return IDKH;
    }

    public void setIDKH(int IDKH) {
        this.IDKH = IDKH;
    }

    public int getIDNV() {
        return IDNV;
    }

    public void setIDNV(int IDNV) {
        this.IDNV = IDNV;
    }

    public int getIDKM() {
        return IDKM;
    }

    public void setIDKM(int IDKM) {
        this.IDKM = IDKM;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getLyDoNghi() {
        return lyDoNghi;
    }

    public void setLyDoNghi(String lyDoNghi) {
        this.lyDoNghi = lyDoNghi;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public float getDonGiaSauGiam() {
        return donGiaSauGiam;
    }

    public void setDonGiaSauGiam(float donGiaSauGiam) {
        this.donGiaSauGiam = donGiaSauGiam;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
