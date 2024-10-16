/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.n2.domainModel.SanPhamChiTiet;
import com.n2.iService.BanHangService;
import com.n2.iService.SanPhamService;
import com.n2.viewModel.ChatLieuViewModel;
import com.n2.viewModel.KichCoViewModel;
import com.n2.viewModel.MauSacViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import com.n2.viewModel.SanPhamViewModel;
import com.n2.viewModel.ThuongHieuViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class SanPhamJP extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    BanHangService banHangService = new BanHangService();
    SanPhamService sm = new SanPhamService();

    /**
     * Creates new form Form_1
     */
    public SanPhamJP() {
        initComponents();
        loadSPCT();
        this.fillTableSP(sm.getAllSP());

        // Trạng thái SP
        cboTrangThaiSP.removeAllItems();
        for (int i = 1; i <= 2; i++) {
            cboTrangThaiSP.addItem(String.valueOf(i));
        }

        // Trạng thái TTMS
        cboTrangThaiThuocTinh.removeAllItems();
        for (int i = 1; i <= 2; i++) {
            cboTrangThaiThuocTinh.addItem(String.valueOf(i));
        }

        // load combobox Màu Sắc
        loadDataToComboBoxMauSac();
        loadDataToComboBoxChatLieu();
        loadDataToComboBoxThuongHieu();
        loadDataToComboBoxKichCo();

    }

    // load danh sách sản phẩm
    public void loadSPCT() {
        List<SanPhamChiTietViewModel> list = banHangService.getAllSPCT();
        model = (DefaultTableModel) tblSPCT.getModel();
        model.setRowCount(0);
        int index = 1;
        for (SanPhamChiTietViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaSPCT(), x.getTenSP(), x.getSoLuongTon(), x.getDonGia(), x.getTenKC(), x.getTenCL(), x.getTenMS(), x.trangThai(x.getTrangThaiSPCT())
            });
            index++;
        }
    }

    // load bảng sản phẩm
    public void fillTableSP(ArrayList<SanPhamViewModel> list) {
        model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0); // xoá dl cũ trong bảng
        for (SanPhamViewModel x : list) {
            model.addRow(x.toDataRow());
        }
    }

    // load bảng Thuộc Tính MS
    public void fillTableMS(ArrayList<MauSacViewModel> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0); // xoá dl cũ trong bảng
        for (MauSacViewModel x : list) {
            model.addRow(x.toDataRow());
        }
    }

    // load bảng Thuộc Tính CL
    public void fillTableCL(ArrayList<ChatLieuViewModel> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0); // xoá dl cũ trong bảng
        for (ChatLieuViewModel x : list) {
            model.addRow(x.toDataRow());
        }
    }

    // load bảng Thuộc Tính TH
    public void fillTableTH(ArrayList<ThuongHieuViewModel> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0); // xoá dl cũ trong bảng
        for (ThuongHieuViewModel x : list) {
            model.addRow(x.toDataRow());
        }
    }

    // load bảng Thuộc Tính KC
    public void fillTableKC(ArrayList<KichCoViewModel> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0); // xoá dl cũ trong bảng
        for (KichCoViewModel x : list) {
            model.addRow(x.toDataRow());
        }
    }

    // readForm Sản Phẩm
    SanPhamViewModel readFormSP() {
        String maSP = txtMaSP.getText().trim();
        String tenSP = txtTenSP.getText().trim();
        int trangThaiSP = Integer.parseInt(cboTrangThaiSP.getSelectedItem().toString());
        return new SanPhamViewModel(maSP, tenSP, trangThaiSP);
    }

    // readForm Thuộc Tính MS
    MauSacViewModel readFormMS() {
        String mathuocTinhMS = txtMaThuocTinh.getText().trim();
        String tenthuocTinhMS = txtTenThuocTinh.getText().trim();
        int trangThaiTTMS = Integer.parseInt(cboTrangThaiThuocTinh.getSelectedItem().toString());
        return new MauSacViewModel(mathuocTinhMS, tenthuocTinhMS, trangThaiTTMS);
    }

    // readForm Thuộc Tính CL
    ChatLieuViewModel readFormCL() {
        String mathuocTinhCL = txtMaThuocTinh.getText().trim();
        String tenthuocTinhCL = txtTenThuocTinh.getText().trim();
        int trangThaiTTCL = Integer.parseInt(cboTrangThaiThuocTinh.getSelectedItem().toString());
        return new ChatLieuViewModel(mathuocTinhCL, tenthuocTinhCL, trangThaiTTCL);
    }

    // readForm Thuộc Tính TH
    ThuongHieuViewModel readFormTH() {
        String mathuocTinhTH = txtMaThuocTinh.getText().trim();
        String tenthuocTinhTH = txtTenThuocTinh.getText().trim();
        int trangThaiTTTH = Integer.parseInt(cboTrangThaiThuocTinh.getSelectedItem().toString());
        return new ThuongHieuViewModel(mathuocTinhTH, tenthuocTinhTH, trangThaiTTTH);
    }

    // readForm Thuộc Tính KC
    KichCoViewModel readFormKC() {
        String mathuocTinhKC = txtMaThuocTinh.getText().trim();
        String tenthuocTinhKC = txtTenThuocTinh.getText().trim();
        int trangThaiTTKC = Integer.parseInt(cboTrangThaiThuocTinh.getSelectedItem().toString());
        return new KichCoViewModel(mathuocTinhKC, tenthuocTinhKC, trangThaiTTKC);
    }

    // Đổ dữ liệu màu sắc vào combobox
    public void loadDataToComboBoxMauSac() {
        SanPhamService sanPhamService = new SanPhamService(); // Khởi tạo service
        ArrayList<MauSacViewModel> listMauSac = sanPhamService.getAllMS(); // Lấy danh sách màu sắc

        // Xóa dữ liệu hiện có trong combobox (nếu cần)
        cboMauSac.removeAllItems();

        // Đổ từng màu sắc vào combobox
        for (MauSacViewModel ms : listMauSac) {
            cboMauSac.addItem(ms.getTenMS()); // Hoặc bạn có thể add mã màu thay vì tên
        }
    }

    // Đổ dữ liệu chất liệu vào combobox
    public void loadDataToComboBoxChatLieu() {
        SanPhamService sanPhamService = new SanPhamService(); // Khởi tạo service
        ArrayList<ChatLieuViewModel> listChatLieu = sanPhamService.getAllCL(); // Lấy danh sách chất liệu

        // Xóa dữ liệu hiện có trong combobox (nếu cần)
        cboChatLieu.removeAllItems();

        // Đổ từng chất liệu vào combobox
        for (ChatLieuViewModel cl : listChatLieu) {
            cboChatLieu.addItem(cl.getTenCL()); // Hoặc bạn có thể add mã màu thay vì tên
        }
    }

    // Đổ dữ liệu Thương Hiệu vào combobox
    public void loadDataToComboBoxThuongHieu() {
        SanPhamService sanPhamService = new SanPhamService(); // Khởi tạo service
        ArrayList<ThuongHieuViewModel> listThuongHieu = sanPhamService.getAllTH(); // Lấy danh sách thương hiệu

        // Xóa dữ liệu hiện có trong combobox (nếu cần)
        cboThuongHieu.removeAllItems();

        // Đổ từng màu sắc vào combobox
        for (ThuongHieuViewModel th : listThuongHieu) {
            cboThuongHieu.addItem(th.getTenTH()); // Hoặc bạn có thể add mã màu thay vì tên
        }
    }

    // Đổ dữ liệu Thương Hiệu vào combobox
    public void loadDataToComboBoxKichCo() {
        SanPhamService sanPhamService = new SanPhamService(); // Khởi tạo service
        ArrayList<KichCoViewModel> listKichCo = sanPhamService.getAllKC(); // Lấy danh sách thương hiệu

        // Xóa dữ liệu hiện có trong combobox (nếu cần)
        cboKichCo.removeAllItems();

        // Đổ từng màu sắc vào combobox
        for (KichCoViewModel kc : listKichCo) {
            cboKichCo.addItem(kc.getTenKC()); // Hoặc bạn có thể add mã màu thay vì tên
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboTrangThaiSP = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaSPCT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        txtSoLuongSPCT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTaSPCT = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        txtDonGiaSPCT = new javax.swing.JTextField();
        btnThemSPCT = new javax.swing.JButton();
        btnSuaSPCT = new javax.swing.JButton();
        btnXoaSPCT = new javax.swing.JButton();
        btnResetSPCT = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cboTrangThaiSPCT = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboTrangThaiThuocTinh = new javax.swing.JComboBox<>();
        btnThemTT = new javax.swing.JButton();
        btnSuaTT = new javax.swing.JButton();
        btnXoaTT = new javax.swing.JButton();
        rboMauSac = new javax.swing.JRadioButton();
        rboChatLieu = new javax.swing.JRadioButton();
        rboThuongHieu = new javax.swing.JRadioButton();
        rboKichCo = new javax.swing.JRadioButton();
        btnResetTT = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();

        setBackground(new java.awt.Color(234, 231, 214));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Quản Lý Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setText("Tên Sản Phẩm");

        jLabel3.setText("Trạng Thái SP");

        cboTrangThaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hàng", "Hết Hàng" }));

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnXoaSP.setText("Xoá");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenSP)
                    .addComponent(txtMaSP)
                    .addComponent(cboTrangThaiSP, 0, 166, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboTrangThaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP))
                .addGap(60, 60, 60))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã Sản Phẩm", "Tên Sản Phẩm", "Trạng Thái"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);
        if (tblSP.getColumnModel().getColumnCount() > 0) {
            tblSP.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblSP.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblSP.getColumnModel().getColumn(2).setPreferredWidth(240);
            tblSP.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Chi Tiết Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel7.setText("Mã SPCT");

        jLabel10.setText("Số Lượng");

        jLabel11.setText("Người Tạo");

        jLabel12.setText("Đơn Giá");

        txtMoTaSPCT.setColumns(20);
        txtMoTaSPCT.setRows(5);
        jScrollPane4.setViewportView(txtMoTaSPCT);

        jLabel13.setText("Mô Tả");

        btnThemSPCT.setText("Thêm");
        btnThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCTActionPerformed(evt);
            }
        });

        btnSuaSPCT.setText("Sửa");

        btnXoaSPCT.setText("Xoá");

        btnResetSPCT.setText("Reset");

        jLabel8.setText("Màu Sắc");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        jLabel9.setText("Chất Liệu");

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        cboKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        jLabel14.setText("Kích Cỡ");

        jLabel15.setText("Thương Hiệu");

        jLabel16.setText("Trạng Thái");

        cboTrangThaiSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSPCT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDonGiaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(167, 167, 167))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTrangThaiSPCT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoaSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnResetSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtSoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDonGiaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cboTrangThaiSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSPCT)
                            .addComponent(btnSuaSPCT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaSPCT)
                            .addComponent(btnResetSPCT)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chi Tiết Sản Phẩm"));

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Còn", "Giá", "Kích Cỡ", "Chất Liệu", "Màu Sắc", "Trạng Thái"
            }
        ));
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSPCT);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản Lý Sản Phẩm", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Quản Lý Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel4.setText("Mã Thuộc tính");

        jLabel5.setText("Tên Thuộc Tính");

        jLabel6.setText("Trạng Thái SP");

        cboTrangThaiThuocTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hàng", "Hết Hàng" }));

        btnThemTT.setText("Thêm");
        btnThemTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTTActionPerformed(evt);
            }
        });

        btnSuaTT.setText("Sửa");
        btnSuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTActionPerformed(evt);
            }
        });

        btnXoaTT.setText("Xoá");

        buttonGroup1.add(rboMauSac);
        rboMauSac.setText("Màu Sắc");
        rboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rboMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rboChatLieu);
        rboChatLieu.setText("Chất Liệu");
        rboChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rboChatLieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rboThuongHieu);
        rboThuongHieu.setText("Thương Hiệu");
        rboThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rboThuongHieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rboKichCo);
        rboKichCo.setText("Kích Cỡ");
        rboKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rboKichCoActionPerformed(evt);
            }
        });

        btnResetTT.setText("Reset");
        btnResetTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThemTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetTT))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTrangThaiThuocTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaThuocTinh)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(rboMauSac)
                .addGap(18, 18, 18)
                .addComponent(rboChatLieu)
                .addGap(18, 18, 18)
                .addComponent(rboThuongHieu)
                .addGap(18, 18, 18)
                .addComponent(rboKichCo)
                .addGap(38, 38, 38))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rboMauSac)
                    .addComponent(rboChatLieu)
                    .addComponent(rboThuongHieu)
                    .addComponent(rboKichCo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboTrangThaiThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTT)
                    .addComponent(btnSuaTT)
                    .addComponent(btnXoaTT)
                    .addComponent(btnResetTT))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã Thuộc Tính", "Tên Thuộc Tính", "Trạng Thái"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc Tính Sản Phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
        int row = this.tblSP.getSelectedRow();
        if (row == -1) {
            return;
        }

        SanPhamViewModel m = this.sm.getAllSP().get(row);
        this.txtMaSP.setText(m.getMaSP());
        this.txtTenSP.setText(m.getTenSP());
        int nam = this.cboTrangThaiSP.getSelectedIndex();
        if (nam == 1) {
            this.cboTrangThaiSP.setSelectedIndex(1);
        } else {
            this.cboTrangThaiSP.setSelectedIndex(2);
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        if (this.readFormSP() != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?");
            if (chon == 0) {
                sm.themSP(this.readFormSP());
                this.fillTableSP(sm.getAllSP());
                JOptionPane.showMessageDialog(this, "them thanh cong");
            } else {
                JOptionPane.showMessageDialog(this, "ban khong chon them");
            }
        } else {
            JOptionPane.showMessageDialog(this, "them that bai");
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int i = tblSP.getSelectedRow();
        // b2: đọc dl đã sửa từ form
        if (this.readFormSP() != null) {
            int id = Integer.parseInt(tblSP.getValueAt(i, 0).toString());
            if (sm.suaSP(id, this.readFormSP()) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                this.fillTableSP(sm.getAllSP());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked

    }//GEN-LAST:event_tblSPCTMouseClicked

    private void rboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rboMauSacActionPerformed
        // TODO add your handling code here:
        this.fillTableMS(sm.getAllMS());
    }//GEN-LAST:event_rboMauSacActionPerformed

    private void rboChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rboChatLieuActionPerformed
        // TODO add your handling code here:\
        this.fillTableCL(sm.getAllCL());
    }//GEN-LAST:event_rboChatLieuActionPerformed

    private void rboThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rboThuongHieuActionPerformed
        // TODO add your handling code here:
        this.fillTableTH(sm.getAllTH());
    }//GEN-LAST:event_rboThuongHieuActionPerformed

    private void rboKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rboKichCoActionPerformed
        // TODO add your handling code here:
        this.fillTableKC(sm.getAllKC());
    }//GEN-LAST:event_rboKichCoActionPerformed

    private void btnThemTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTTActionPerformed
        // TODO add your handling code here:
        if (rboMauSac.isSelected()) {
            if (this.readFormMS() != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm Màu Sắc không ?");
                if (chon == 0) {
                    sm.themTTMS(this.readFormMS());
                    this.fillTableMS(sm.getAllMS());
                    loadDataToComboBoxMauSac();
                    JOptionPane.showMessageDialog(this, "thêm Màu Sắc thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "bạn không chọn thêm");
                }
            } else {
                JOptionPane.showMessageDialog(this, "them that bai");
            }
        } else if (rboChatLieu.isSelected()) {
            if (this.readFormCL() != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm Chất Liệu không ?");
                if (chon == 0) {
                    sm.themTTCL(this.readFormCL());
                    this.fillTableCL(sm.getAllCL());
                    loadDataToComboBoxChatLieu();
                    JOptionPane.showMessageDialog(this, "thêm Chất Liệu thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "bạn không chọn thêm");
                }
            } else {
                JOptionPane.showMessageDialog(this, "them that bai");
            }
        } else if (rboThuongHieu.isSelected()) {
            if (this.readFormTH() != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm Thương Hiệu không ?");
                if (chon == 0) {
                    sm.themTTTH(this.readFormTH());
                    this.fillTableTH(sm.getAllTH());
                    loadDataToComboBoxThuongHieu();
                    JOptionPane.showMessageDialog(this, "thêm Thương Hiệu thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "ban không chọn thêm");
                }
            } else {
                JOptionPane.showMessageDialog(this, "them that bai");
            }
        } else if (rboKichCo.isSelected()) {
            if (this.readFormKC() != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm Kích Cỡ không ?");
                if (chon == 0) {
                    sm.themTTKC(this.readFormKC());
                    this.fillTableKC(sm.getAllKC());
                    loadDataToComboBoxKichCo();
                    JOptionPane.showMessageDialog(this, "thêm Kích Cỡ thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "bạn không chọn thêm");
                }
            } else {
                JOptionPane.showMessageDialog(this, "them that bai");
            }
        }

    }//GEN-LAST:event_btnThemTTActionPerformed

    private void btnSuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTActionPerformed
        // TODO add your handling code here:
        int i = tblThuocTinh.getSelectedRow();

        if (rboMauSac.isSelected()) {
            if (this.readFormMS() != null) {
                int id = Integer.parseInt(tblThuocTinh.getValueAt(i, 0).toString());
                if (sm.suaTTMS(id, this.readFormMS()) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Màu Sắc Thành Công");
                    this.fillTableMS(sm.getAllMS());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Màu Sắc thất bại");
                }
            }
        } else if (rboChatLieu.isSelected()) {
            if (this.readFormCL() != null) {
                int id = Integer.parseInt(tblThuocTinh.getValueAt(i, 0).toString());
                if (sm.suaTTCL(id, this.readFormCL()) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Chất Liệu Thành Công");
                    this.fillTableCL(sm.getAllCL());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Chất Liệu thất bại");
                }
            }
        } else if (rboThuongHieu.isSelected()) {
            if (this.readFormTH() != null) {
                int id = Integer.parseInt(tblThuocTinh.getValueAt(i, 0).toString());
                if (sm.suaTTTH(id, this.readFormTH()) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Thương Hiệu Thành Công");
                    this.fillTableTH(sm.getAllTH());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Thương Hiệu thất bại");
                }
            }
        } else if (rboKichCo.isSelected()) {
            if (this.readFormKC() != null) {
                int id = Integer.parseInt(tblThuocTinh.getValueAt(i, 0).toString());
                if (sm.suaTTKC(id, this.readFormKC()) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Kích Cỡ Thành Công");
                    this.fillTableKC(sm.getAllKC());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Kích Cỡ thất bại");
                }
            }

        }

    }//GEN-LAST:event_btnSuaTTActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked

        int row = this.tblThuocTinh.getSelectedRow();
        if (row == -1) {
            return;
        }

        if (rboMauSac.isSelected()) {
            MauSacViewModel m = this.sm.getAllMS().get(row);
            this.txtMaThuocTinh.setText(m.getMaMS());
            this.txtTenThuocTinh.setText(m.getTenMS());
            int nam = this.cboTrangThaiThuocTinh.getSelectedIndex();
            if (nam == 1) {
                this.cboTrangThaiThuocTinh.setSelectedIndex(1);
            } else {
                this.cboTrangThaiThuocTinh.setSelectedIndex(2);
            }

        } else if (rboChatLieu.isSelected()) {
            ChatLieuViewModel m = this.sm.getAllCL().get(row);
            this.txtMaThuocTinh.setText(m.getMaCL());
            this.txtTenThuocTinh.setText(m.getTenCL());
            int nam = this.cboTrangThaiThuocTinh.getSelectedIndex();
            if (nam == 1) {
                this.cboTrangThaiThuocTinh.setSelectedIndex(1);
            } else {
                this.cboTrangThaiThuocTinh.setSelectedIndex(2);
            }

        } else if (rboThuongHieu.isSelected()) {
            ThuongHieuViewModel m = this.sm.getAllTH().get(row);
            this.txtMaThuocTinh.setText(m.getMaTH());
            this.txtTenThuocTinh.setText(m.getTenTH());
            int nam = this.cboTrangThaiThuocTinh.getSelectedIndex();
            if (nam == 1) {
                this.cboTrangThaiThuocTinh.setSelectedIndex(1);
            } else {
                this.cboTrangThaiThuocTinh.setSelectedIndex(2);
            }

        } else if (rboKichCo.isSelected()) {
            KichCoViewModel m = this.sm.getAllKC().get(row);
            this.txtMaThuocTinh.setText(m.getMaKC());
            this.txtTenThuocTinh.setText(m.getTenKC());
            int nam = this.cboTrangThaiThuocTinh.getSelectedIndex();
            if (nam == 1) {
                this.cboTrangThaiThuocTinh.setSelectedIndex(1);
            } else {
                this.cboTrangThaiThuocTinh.setSelectedIndex(2);
            }

        }

    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void btnResetTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTTActionPerformed
        // TODO add your handling code here:
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnResetTTActionPerformed

    private void btnThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCTActionPerformed
        // TODO add your handling code here:
        // Lấy dữ liệu từ form
        String maSPCT = txtMaSPCT.getText();
        String nguoiTao = txtNguoiTao.getText();
        int soLuong = Integer.parseInt(txtSoLuongSPCT.getText());
        double donGia = Double.parseDouble(txtDonGiaSPCT.getText());
        String moTa = txtMoTaSPCT.getText();

        // Lấy giá trị từ combobox
        MauSacViewModel mauSac = (MauSacViewModel) cboMauSac.getSelectedItem();
        ChatLieuViewModel chatLieu = (ChatLieuViewModel) cboChatLieu.getSelectedItem();
        ThuongHieuViewModel thuongHieu = (ThuongHieuViewModel) cboThuongHieu.getSelectedItem();
        KichCoViewModel kichCo = (KichCoViewModel) cboKichCo.getSelectedItem();
        int trangThai = Integer.parseInt(cboTrangThaiSPCT.getSelectedItem().toString());

        // Tạo đối tượng SanPhamChiTiet
        SanPhamChiTiet spct = new SanPhamChiTiet();

        // Gọi phương thức service để lưu sản phẩm chi tiết vào database
        SanPhamService sanPhamService = new SanPhamService();
        int result = sanPhamService.themSPCT(spct);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công!");
            // Cập nhật lại bảng danh sách sản phẩm chi tiết nếu có
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại!");
        }
    }//GEN-LAST:event_btnThemSPCTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetSPCT;
    private javax.swing.JButton btnResetTT;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaSPCT;
    private javax.swing.JButton btnSuaTT;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSPCT;
    private javax.swing.JButton btnThemTT;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaSPCT;
    private javax.swing.JButton btnXoaTT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboTrangThaiSP;
    private javax.swing.JComboBox<String> cboTrangThaiSPCT;
    private javax.swing.JComboBox<String> cboTrangThaiThuocTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rboChatLieu;
    private javax.swing.JRadioButton rboKichCo;
    private javax.swing.JRadioButton rboMauSac;
    private javax.swing.JRadioButton rboThuongHieu;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtDonGiaSPCT;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextArea txtMoTaSPCT;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSoLuongSPCT;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables
}
