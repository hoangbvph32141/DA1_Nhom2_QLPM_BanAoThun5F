package com.n2.viewModel;

public class ThuongHieuViewModel {

    private int IDTH;
    private String maTH;
    private String tenTH;
    private int trangThaiTH;

    public ThuongHieuViewModel(int IDTH, String maTH, String tenTH, int trangThaiTH) {
        this.IDTH = IDTH;
        this.maTH = maTH;
        this.tenTH = tenTH;
        this.trangThaiTH = trangThaiTH;
    }

    public ThuongHieuViewModel(int IDTH) {
        this.IDTH = IDTH;
    }

    public ThuongHieuViewModel(String maTH, String tenTH, int trangThaiTH) {
        this.maTH = maTH;
        this.tenTH = tenTH;
        this.trangThaiTH = trangThaiTH;
    }

    public ThuongHieuViewModel(String tenTH) {
        this.tenTH = tenTH;
    }

    public ThuongHieuViewModel() {
    }

    public int getIDTH() {
        return IDTH;
    }

    public void setIDTH(int IDTH) {
        this.IDTH = IDTH;
    }

    public String getMaTH() {
        return maTH;
    }

    public void setMaTH(String maTH) {
        this.maTH = maTH;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public int getTrangThaiTH() {
        return trangThaiTH;
    }

    public void setTrangThaiTH(int trangThaiTH) {
        this.trangThaiTH = trangThaiTH;
    }

    public String trangThai(int trangThai) {
        if (trangThai == 1) {
            return "Còn Hàng";
        } else {
            return "Hết Hàng";
        }

    }

    public Object[] toDataRow() {
        return new Object[]{this.IDTH, this.maTH, this.tenTH, trangThai(trangThaiTH)};

    }

}
