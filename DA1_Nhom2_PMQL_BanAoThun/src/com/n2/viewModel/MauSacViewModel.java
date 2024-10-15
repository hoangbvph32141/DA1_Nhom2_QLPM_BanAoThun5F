package com.n2.viewModel;

public class MauSacViewModel {

    private int IDMS;
    private String maMS;
    private String tenMS;
    private int trangThaiMS;

    public MauSacViewModel(int IDMS, String maMS, String tenMS, int trangThaiMS) {
        this.IDMS = IDMS;
        this.maMS = maMS;
        this.tenMS = tenMS;
        this.trangThaiMS = trangThaiMS;
    }

    public MauSacViewModel() {
    }

    public int getIDMS() {
        return IDMS;
    }

    public void setIDMS(int IDMS) {
        this.IDMS = IDMS;
    }

    public String getMaMS() {
        return maMS;
    }

    public void setMaMS(String maMS) {
        this.maMS = maMS;
    }

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public int getTrangThaiMS() {
        return trangThaiMS;
    }

    public void setTrangThaiMS(int trangThaiMS) {
        this.trangThaiMS = trangThaiMS;
    }

    //
    public String trangThai(int trangThai) {
        if (trangThai == 1) {
            return "Còn Hàng";
        } else {
            return "Hết Hàng";
        }

    }

    public Object[] toDataRow() {
        return new Object[]{this.IDMS, this.maMS, this.tenMS, this.trangThaiMS};

    }

}
