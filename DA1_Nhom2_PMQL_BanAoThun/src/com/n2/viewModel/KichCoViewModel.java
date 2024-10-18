package com.n2.viewModel;

public class KichCoViewModel {

    private int IDKC;
    private String maKC;
    private String tenKC;
    private int trangThaiKC;

    public KichCoViewModel(int IDKC, String maKC, String tenKC, int trangThaiKC) {
        this.IDKC = IDKC;
        this.maKC = maKC;
        this.tenKC = tenKC;
        this.trangThaiKC = trangThaiKC;
    }

    public KichCoViewModel(String maKC, String tenKC, int trangThaiKC) {
        this.maKC = maKC;
        this.tenKC = tenKC;
        this.trangThaiKC = trangThaiKC;
    }

    public KichCoViewModel() {
    }

    public int getIDKC() {
        return IDKC;
    }

    public void setIDKC(int IDKC) {
        this.IDKC = IDKC;
    }

    public String getMaKC() {
        return maKC;
    }

    public void setMaKC(String maKC) {
        this.maKC = maKC;
    }

    public String getTenKC() {
        return tenKC;
    }

    public void setTenKC(String tenKC) {
        this.tenKC = tenKC;
    }

    public int getTrangThaiKC() {
        return trangThaiKC;
    }

    public void setTrangThaiKC(int trangThaiKC) {
        this.trangThaiKC = trangThaiKC;
    }

    public String trangThai(int trangThai) {
        if (trangThai == 1) {
            return "Còn Hàng";
        } else {
            return "Hết Hàng";
        }

    }

    public Object[] toDataRow() {
        return new Object[]{this.IDKC, this.maKC, this.tenKC, trangThai(trangThaiKC)};

    }

}
