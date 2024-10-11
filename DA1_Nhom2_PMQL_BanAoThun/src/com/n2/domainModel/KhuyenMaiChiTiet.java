/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.domainModel;

/**
 *
 * @author PC
 */
public class KhuyenMaiChiTiet {
    private int idKMCT;
    private int idKM;
    private String maKMCT;
    private int trangThaiKMCT;

    public KhuyenMaiChiTiet() {
    }

    public KhuyenMaiChiTiet(int idKMCT, int idKM, String maKMCT, int trangThaiKMCT) {
        this.idKMCT = idKMCT;
        this.idKM = idKM;
        this.maKMCT = maKMCT;
        this.trangThaiKMCT = trangThaiKMCT;
    }

    public int getIdKMCT() {
        return idKMCT;
    }

    public void setIdKMCT(int idKMCT) {
        this.idKMCT = idKMCT;
    }

    public int getIdKM() {
        return idKM;
    }

    public void setIdKM(int idKM) {
        this.idKM = idKM;
    }

    public String getMaKMCT() {
        return maKMCT;
    }

    public void setMaKMCT(String maKMCT) {
        this.maKMCT = maKMCT;
    }

    public int getTrangThaiKMCT() {
        return trangThaiKMCT;
    }

    public void setTrangThaiKMCT(int trangThaiKMCT) {
        this.trangThaiKMCT = trangThaiKMCT;
    }
    
    
}
