/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.n2.domainModel.NhanVien;
import com.n2.iService.NhanVienService;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class NhanVienJP extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    NhanVienService nhanVienService = new NhanVienService();
    int index;

    public NhanVienJP() {
        initComponents();
        getListNhanVien();
        if (tblDanhSachNV.getRowCount() > 0) {
            index = 0;
            showDetail();
        }
    }

    private void getListNhanVien() {
        defaultTableModel = (DefaultTableModel) tblDanhSachNV.getModel();
        defaultTableModel.setRowCount(0);
        int index = 1;
        for (NhanVien nhanVien : nhanVienService.getListNhanVien()) {
            defaultTableModel.addRow(new Object[]{
                index++,
                nhanVien.getIdNV(),
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getGioiTinh() == 0 ? "Nữ" : "Nam",
                nhanVien.getNgaySinh(),
                nhanVien.getCccd(),
                nhanVien.getDiaChi(),
                nhanVien.getSdt(),
                nhanVien.getEmail(),
                nhanVien.getMatKhau(),
                nhanVien.getTrangThai() == 0 ? "Đã Nghỉ" : "Đang Làm",
                nhanVien.getChucVu() == 1 ? "Nhân Viên" : "Quản Lý",});
        }
    }

    private void showDetail() {
        ArrayList<NhanVien> listNVFromSearch = nhanVienService.searchNV(txtTimNV.getText());
        NhanVien nhanVien = listNVFromSearch.get(index);
        txtMaNV.setText(nhanVien.getMaNV());
        txtTenNV.setText(nhanVien.getTenNV());
        if (nhanVien.getGioiTinh() == 0) {
            rdoNu.setSelected(true);
        } else {
            rdoNam.setSelected(true);
        }
        txtNgaySinh.setText(nhanVien.getNgaySinh().toString());
        txtCCCD.setText(nhanVien.getCccd());
        txtDiaChi.setText(nhanVien.getDiaChi());
        txtSDT.setText(nhanVien.getSdt());
        txtEmail.setText(nhanVien.getEmail());
        txtMatKhau.setText(nhanVien.getMatKhau());
        if (nhanVien.getTrangThai() == 0) {
            rdoDaNghi.setSelected(true);
        } else {
            rdoDangLam.setSelected(true);
        }
        if (nhanVien.getChucVu() == 1) {
            cboChucVu.setSelectedIndex(0);
        } else {
            cboChucVu.setSelectedIndex(1);
        }
    }

    private void getlistNVFromSearch() {
        defaultTableModel = (DefaultTableModel) tblDanhSachNV.getModel();
        defaultTableModel.setRowCount(0);
        int index = 1;
        for (NhanVien nhanVien : nhanVienService.searchNV(txtTimNV.getText())) {
            defaultTableModel.addRow(new Object[]{
                index++,
                nhanVien.getIdNV(),
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getGioiTinh() == 0 ? "Nữ" : "Nam",
                nhanVien.getNgaySinh(),
                nhanVien.getCccd(),
                nhanVien.getDiaChi(),
                nhanVien.getSdt(),
                nhanVien.getEmail(),
                nhanVien.getMatKhau(),
                nhanVien.getTrangThai() == 0 ? "Đã Nghỉ" : "Đang Làm",
                nhanVien.getChucVu() == 1 ? "Nhân Viên" : "Quản Lý",});
        }
    }

    private NhanVien getNhanVienFromInputThem() throws ParseException, SQLException { //C nay lấy dữ liệu từ text để thêm đối tượng trong list, cái mà sẽ được thêm mới sang database nhờ repository
        //Có cái này mới có thể add vì nó là công cụ lấy dữ liệu từ textFields về. 

        NhanVien nhanVien = new NhanVien();
        String maDonVi = "NV0";
        String maHangChuc = "NV";
        String maNV;
        int soMa = nhanVienService.countNV() + 1;
        if (soMa < 10) {
            maNV = maDonVi + soMa;
        } else {
            maNV = maHangChuc + soMa;
        }
        nhanVien.setMaNV(maNV);
        nhanVien.setTenNV(txtTenNV.getText());
        if (rdoNu.isSelected()) {
            nhanVien.setGioiTinh(0);
        } else {
            nhanVien.setGioiTinh(1);
        }
        SimpleDateFormat setKieuNgay = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = setKieuNgay.parse(txtNgaySinh.getText());
        nhanVien.setNgaySinh(ngaySinh);
        String cccd = txtCCCD.getText().trim();
        if (nhanVienService.checkCccdExists(cccd)) {
            JOptionPane.showMessageDialog(this, "CCCD đã tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
            return null;
        }
        nhanVien.setCccd(txtCCCD.getText());
        nhanVien.setDiaChi(txtDiaChi.getText());
        String sdt = txtSDT.getText().trim();
        if (nhanVienService.checkSdtExists(sdt)) {
            JOptionPane.showMessageDialog(this, "SĐT đã tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
            return null;
        }
        nhanVien.setSdt(txtSDT.getText());
        String email = txtEmail.getText().trim();
        if (nhanVienService.checkEmailExists(email)) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
            return null;
        }
        nhanVien.setEmail(txtEmail.getText());
        nhanVien.setMatKhau(txtMatKhau.getText());
        if (rdoDaNghi.isSelected()) {
            nhanVien.setTrangThai(0);
        } else {
            nhanVien.setTrangThai(1);
        }
        if (cboChucVu.getSelectedIndex() == 0) {
            nhanVien.setChucVu(1); //NV
        } else {
            nhanVien.setChucVu(2); //QL
        }
        return nhanVien;
    }

    private NhanVien getNhanVienFromInputSua() throws SQLException, ParseException { //C nay lấy dữ liệu từ text để thêm đối tượng trong list, cái mà sẽ được thêm mới sang database nhờ repository
        //Có cái này mới có thể add vì nó là công cụ lấy dữ liệu từ textFields về. 
        int row = tblDanhSachNV.getSelectedRow();
        NhanVien nhanVien = new NhanVien();
        nhanVien.setIdNV(Integer.valueOf(tblDanhSachNV.getValueAt(row, 1).toString()));
        nhanVien.setTenNV(txtTenNV.getText());
        if (rdoNu.isSelected()) {
            nhanVien.setGioiTinh(0);
        } else {
            nhanVien.setGioiTinh(1);
        }
        SimpleDateFormat setKieuNgay = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = setKieuNgay.parse(txtNgaySinh.getText());
        nhanVien.setNgaySinh(ngaySinh);
//        String cccd = txtCCCD.getText().trim();
//        if (cccd.equals(nhanVien.getCccd())) {
//            if (nhanVienService.checkCccdExists(cccd)) {
//                JOptionPane.showMessageDialog(this, "CCCD đã tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
//                return null;
//            }
//        }
        nhanVien.setCccd(txtCCCD.getText());
        nhanVien.setDiaChi(txtDiaChi.getText());
//        String sdt = txtSDT.getText().trim();
//        if (!sdt.equals(nhanVien.getSdt())) {
//            if (nhanVienService.checkSdtExists(sdt)) {
//                JOptionPane.showMessageDialog(this, "SĐT đã tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
//                return null;
//            }
//        }

        nhanVien.setSdt(txtSDT.getText());
//        String email = txtEmail.getText().trim();
//        if (email.equals(nhanVien.getEmail())) {
//            if (nhanVienService.checkEmailExists(email)) {
//                JOptionPane.showMessageDialog(this, "Email đã tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
//                return null;
//            }
//        }
        nhanVien.setEmail(txtEmail.getText());
        nhanVien.setMatKhau(txtMatKhau.getText());
        if (rdoDaNghi.isSelected()) {
            nhanVien.setTrangThai(0);
        } else {
            nhanVien.setTrangThai(1);
        }
        if (cboChucVu.getSelectedIndex() == 0) {
            nhanVien.setChucVu(1); //NV
        } else {
            nhanVien.setChucVu(2); //QL
        }
        return nhanVien;
    }

    private NhanVien getNhanVienFromInputTTNV() throws SQLException, ParseException {
        int row = tblDanhSachNV.getSelectedRow();
        NhanVien nhanVien = new NhanVien();
        if (rdoDaNghi.isSelected()) {
            nhanVien.setTrangThai(1);
        } else {
            nhanVien.setTrangThai(0);
        }
        nhanVien.setIdNV(Integer.valueOf(tblDanhSachNV.getValueAt(row, 1).toString()));
        return nhanVien;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        lblMaHD = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnThayDoiTTNV = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnSuaNV = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        rdoDangLam = new javax.swing.JRadioButton();
        rdoDaNghi = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachNV = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTimNV = new javax.swing.JButton();
        txtTimNV = new javax.swing.JTextField();

        setBackground(new java.awt.Color(234, 231, 214));

        jPanel2.setBackground(new java.awt.Color(176, 212, 184));

        lblMaHD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMaHD.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("CCCD:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Địa Chỉ:");

        txtMaNV.setText("?");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Mã NV:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tên NV:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Ngày Sinh:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Giới Tính:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Mật Khẩu");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Trạng Thái:");

        btnThayDoiTTNV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThayDoiTTNV.setText("Đổi TT");
        btnThayDoiTTNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiTTNVActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Chức Vụ:");

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Quản Lý" }));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("SDT:");

        btnSuaNV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSuaNV.setText("Sửa NV");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnThemNV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemNV.setText("Thêm NV");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Email:");

        buttonGroup2.add(rdoDangLam);
        rdoDangLam.setSelected(true);
        rdoDangLam.setText("Đang Làm");

        buttonGroup2.add(rdoDaNghi);
        rdoDaNghi.setText("Đã Nghỉ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu))
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(245, 245, 245))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(cboChucVu, 0, 181, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rdoDangLam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoDaNghi))
                                    .addComponent(txtMatKhau))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addGap(208, 208, 208)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThemNV)
                        .addGap(15, 15, 15)
                        .addComponent(btnSuaNV)
                        .addGap(15, 15, 15)
                        .addComponent(btnThayDoiTTNV)))
                .addGap(145, 145, 145)
                .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaNV, btnThayDoiTTNV, btnThemNV});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaNV))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoDangLam)
                                        .addComponent(rdoDaNghi))
                                    .addComponent(jLabel14))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSuaNV)
                                    .addComponent(btnThemNV)
                                    .addComponent(btnThayDoiTTNV)))))
                    .addComponent(lblMaHD))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaNV, btnThayDoiTTNV, btnThemNV});

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Nhân Viên");

        tblDanhSachNV.setBackground(new java.awt.Color(176, 212, 184));
        tblDanhSachNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "IDNV", "Mã NV", "Tên NV", "Giới Tính", "Ngày Sinh", "CCCD", "Địa Chỉ", "SĐT", "Email", "MK", "Trạng Thái", "Chức Vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachNV);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Danh Sách Nhân Viên");

        jPanel4.setBackground(new java.awt.Color(176, 212, 184));

        btnTimNV.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTimNV.setText("Tìm NV");
        btnTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTimNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimNV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 784, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachNVMouseClicked
        // TODO add your handling code here:
        if (tblDanhSachNV.getRowCount() > -1) {
            index = tblDanhSachNV.getSelectedRow();
            showDetail();
        }
    }//GEN-LAST:event_tblDanhSachNVMouseClicked

    private void btnTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNVActionPerformed
        // TODO add your handling code here:
        getlistNVFromSearch();
    }//GEN-LAST:event_btnTimNVActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
//        if (txtMaNV.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "mã thiếu.");
//            return;
//        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm nhân viên " + txtTenNV.getText() + " không?");
        if (confirm == JOptionPane.OK_OPTION) {
            try {
                NhanVien nhanVien = getNhanVienFromInputThem();
                if (nhanVien == null) {
                    // Nếu nhanVien là null, dừng lại, không thêm vào database
                    return;
                }
                if (nhanVienService.themNV(nhanVien) != 0) {
                    JOptionPane.showMessageDialog(this, "Add done.");
                    getListNhanVien(); //Load lại list sau khi thêm để hiển thị lên bảng
                    showDetail();
                } else {
                    JOptionPane.showMessageDialog(this, "Add false.");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienJP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienJP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        index = tblDanhSachNV.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên mà bạn muốn sửa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa nhân viên " + txtTenNV.getText() + " không?");
        if (confirm == JOptionPane.OK_OPTION) {
            try {
                NhanVien nhanVien = getNhanVienFromInputSua();
                if (nhanVien == null) {
                    // Nếu nhanVien là null, dừng lại, không thêm vào database
                    return;
                }
                if (nhanVienService.suaNV(nhanVien) != 0) {
                    JOptionPane.showMessageDialog(this, "Edit done.");
                    getListNhanVien(); //Load lại list sau khi thêm để hiển thị lên bảng
                    showDetail();
                } else {
                    JOptionPane.showMessageDialog(this, "Edit false.");
                    return;
                }
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(NhanVienJP.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình sửa: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnThayDoiTTNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiTTNVActionPerformed
        // TODO add your handling code here:
        index = tblDanhSachNV.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên mà bạn muốn đổi trạng thái!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đổi rạng thái nhân viên " + txtTenNV.getText() + " không?");
        if (confirm == JOptionPane.OK_OPTION) {
            try {
                NhanVien nhanVien = getNhanVienFromInputTTNV();
                if (nhanVienService.doiTTNV(nhanVien) != 0) {
                    JOptionPane.showMessageDialog(this, "Đổi TT nhân viên thành công.");
                    getListNhanVien(); //Load lại list sau khi thêm để hiển thị lên bảng
                    showDetail();
                } else {
                    JOptionPane.showMessageDialog(this, "Đổi TT nhân viên thất bại.");
                    return;
                }
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(NhanVienJP.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình đổi TT nhân viên: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnThayDoiTTNVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThayDoiTTNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnTimNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JRadioButton rdoDaNghi;
    private javax.swing.JRadioButton rdoDangLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblDanhSachNV;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables

}
