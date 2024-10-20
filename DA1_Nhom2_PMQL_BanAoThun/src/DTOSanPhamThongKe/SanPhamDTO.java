/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSanPhamThongKe;

/**
 *
 * @author admin
 */
public class SanPhamDTO {
    private String ma;
    private String ten;
    private int soLuong;
    private float doanhThu;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String ma, String ten, int soLuong, float doanhThu) {
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.doanhThu = doanhThu;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }
    
    
}
