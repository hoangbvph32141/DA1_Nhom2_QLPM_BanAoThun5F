package com.n2.viewModel;

public class SanPhamViewModel {
    
    private int ID;
    private String maSP;
    private String tenSP;
    private int trangThaiSP;
    
    public SanPhamViewModel() {
    }
    
    public SanPhamViewModel(int ID, String maSP, String tenSP, int trangThaiSP) {
        this.ID = ID;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.trangThaiSP = trangThaiSP;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
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
    
    public int getTrangThaiSP() {
        return trangThaiSP;
    }
    
    public void setTrangThaiSP(int trangThaiSP) {
        this.trangThaiSP = trangThaiSP;
    }
    
    public String trangThai(int trangThai) {
        if (trangThai == 1) {
            return "Còn Hàng";
        } else {
            return "Hết Hàng";
        }
        
    }
    
    public Object[] toDataRow() {
        return new Object[]{this.ID, this.maSP, this.tenSP, trangThai(trangThaiSP)};
    }
    
}
