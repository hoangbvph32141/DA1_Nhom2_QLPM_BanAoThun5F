/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.domainModel;

/**
 *
 * @author PC
 */
public class DangNhap {
    private int id;
    private String tenDangNhap;

    public DangNhap() {
    }

    public DangNhap(int id, String tenDangNhap) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    
    
}
