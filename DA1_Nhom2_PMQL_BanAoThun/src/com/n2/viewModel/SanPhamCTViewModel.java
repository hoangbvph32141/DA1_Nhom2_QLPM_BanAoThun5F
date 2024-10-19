package com.n2.viewModel;

public class SanPhamCTViewModel {

    private int idSPCT;
    private MauSacViewModel msvm;
    private ChatLieuViewModel clvm;
    private ThuongHieuViewModel thvm;
    private KichCoViewModel kcvm;
    private SanPhamViewModel spvm;
    private String maSPCT;
    private String nguoiTao;
    private int soLuongTon;
    private String moTa;
    private int trangThai;
    private float donGia;

    // Constructor khởi tạo từ các đối tượng ViewModel
    public SanPhamCTViewModel(MauSacViewModel msvm, ChatLieuViewModel clvm, ThuongHieuViewModel thvm,
            KichCoViewModel kcvm, SanPhamViewModel spvm, String maSPCT, String nguoiTao,
            int soLuongTon, String moTa, int trangThai, float donGia) {
        this.msvm = msvm;
        this.clvm = clvm;
        this.thvm = thvm;
        this.kcvm = kcvm;
        this.spvm = spvm;
        this.maSPCT = maSPCT;
        this.nguoiTao = nguoiTao;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.donGia = donGia;
    }

    public SanPhamCTViewModel(int mauSacId, int chatLieuId, int thuongHieuId, int kichCoId, int sanPhamId, String maSPCT, String nguoiTao, int soLuongTon, String moTa, int trangThai, float donGia) {
        this.msvm = new MauSacViewModel(mauSacId);   // Khởi tạo ViewModel từ ID
        this.clvm = new ChatLieuViewModel(chatLieuId);
        this.thvm = new ThuongHieuViewModel(thuongHieuId);
        this.kcvm = new KichCoViewModel(kichCoId);
        this.spvm = new SanPhamViewModel(sanPhamId);
        this.maSPCT = maSPCT;
        this.nguoiTao = nguoiTao;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.donGia = donGia;
    }

    public SanPhamCTViewModel(String tenSM, String tenChatLieu, String tenThuongHieu, String tenKichCo, int sanPhamId, String maSPCT, String nguoiTao, int soLuongTon, String moTa, int trangThai, float donGia) {
        this.msvm = new MauSacViewModel(tenSM);   // Khởi tạo ViewModel từ ID
        this.clvm = new ChatLieuViewModel(tenChatLieu);
        this.thvm = new ThuongHieuViewModel(tenThuongHieu);
        this.kcvm = new KichCoViewModel(tenKichCo);
        this.spvm = new SanPhamViewModel(sanPhamId);
        this.maSPCT = maSPCT;
        this.nguoiTao = nguoiTao;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.donGia = donGia;
    }

    public MauSacViewModel getMsvm() {
        return msvm;
    }

    public void setMsvm(MauSacViewModel msvm) {
        this.msvm = msvm;
    }

    public ChatLieuViewModel getClvm() {
        return clvm;
    }

    public void setClvm(ChatLieuViewModel clvm) {
        this.clvm = clvm;
    }

    public ThuongHieuViewModel getThvm() {
        return thvm;
    }

    public void setThvm(ThuongHieuViewModel thvm) {
        this.thvm = thvm;
    }

    public KichCoViewModel getKcvm() {
        return kcvm;
    }

    public void setKcvm(KichCoViewModel kcvm) {
        this.kcvm = kcvm;
    }

    public SanPhamViewModel getSpvm() {
        return spvm;
    }

    public void setSpvm(SanPhamViewModel spvm) {
        this.spvm = spvm;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.msvm != null ? msvm.getIDMS() : null, // Gọi phương thức getId() của MauSacViewModel
            this.clvm != null ? clvm.getIDCL() : null, // Gọi phương thức getId() của ChatLieuViewModel
            this.thvm != null ? thvm.getIDTH() : null, // Gọi phương thức getId() của ThuongHieuViewModel
            this.kcvm != null ? kcvm.getIDKC() : null, // Gọi phương thức getId() của KichCoViewModel
            this.spvm != null ? spvm.getID() : null, // Gọi phương thức getId() của SanPhamViewModel nếu cần
            this.maSPCT,
            this.nguoiTao,
            this.soLuongTon,
            this.moTa,
            this.trangThai,
            this.donGia
        };
    }

    public int getIdMS() {
        return msvm != null ? msvm.getIDMS() : -1; // Trả về ID màu sắc hoặc -1 nếu msvm là null
    }

    // Getter cho ChatLieu
    public int getIdCL() {
        return clvm != null ? clvm.getIDCL() : -1; // Trả về ID chất liệu hoặc -1 nếu clvm là null
    }

    // Getter cho ThuongHieu
    public int getIdTH() {
        return thvm != null ? thvm.getIDTH() : -1; // Trả về ID thương hiệu hoặc -1 nếu thvm là null
    }

    // Getter cho KichCo
    public int getIdKC() {
        return kcvm != null ? kcvm.getIDKC() : -1; // Trả về ID kích cỡ hoặc -1 nếu kcvm là null
    }

    // Getter cho SanPham
    public int getIdSP() {
        return spvm != null ? spvm.getID() : -1; // Trả về ID sản phẩm hoặc -1 nếu spvm là null
    }

    public int getTrangThaiSPCT() {
        return trangThai;
    }

}
