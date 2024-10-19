package com.n2.viewModel;

public class ChatLieuViewModel {

    private int IDCL;
    private String maCL;
    private String tenCL;
    private int trangThaiMS;

    public ChatLieuViewModel(int IDCL, String maCL, String tenCL, int trangThaiMS) {
        this.IDCL = IDCL;
        this.maCL = maCL;
        this.tenCL = tenCL;
        this.trangThaiMS = trangThaiMS;
    }

    public ChatLieuViewModel(String maCL, String tenCL, int trangThaiMS) {
        this.maCL = maCL;
        this.tenCL = tenCL;
        this.trangThaiMS = trangThaiMS;
    }

    public ChatLieuViewModel(int IDCL) {
        this.IDCL = IDCL;
    }

    public ChatLieuViewModel(String tenCL) {
        this.tenCL = tenCL;
    }

    public ChatLieuViewModel() {
    }

    public int getIDCL() {
        return IDCL;
    }

    public void setIDCL(int IDCL) {
        this.IDCL = IDCL;
    }

    public String getMaCL() {
        return maCL;
    }

    public void setMaCL(String maCL) {
        this.maCL = maCL;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public int getTrangThaiMS() {
        return trangThaiMS;
    }

    public void setTrangThaiMS(int trangThaiMS) {
        this.trangThaiMS = trangThaiMS;
    }

    public String trangThai(int trangThai) {
        if (trangThai == 1) {
            return "Còn Hàng";
        } else {
            return "Hết Hàng";
        }

    }

    public Object[] toDataRow() {
        return new Object[]{this.IDCL, this.maCL, this.tenCL, trangThai(trangThaiMS)};

    }

}
