/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.n2.domainModel.KhuyenMai;
import com.n2.iService.KhuyenMaiService;
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
public class KhuyenMaiJP extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    int index;

    public KhuyenMaiJP() {
        initComponents();
        getKhuyenMai();
        getSanPham();
        if (tblDanhSachKM.getRowCount() > 0) {
            index = 0;
            showDetail();
        }
    }

    private void getKhuyenMai() {
        defaultTableModel = (DefaultTableModel) tblDanhSachKM.getModel();
        defaultTableModel.setRowCount(0);
        int index = 1;
        for (KhuyenMai khuyenMai : khuyenMaiService.getListKhuyenMai()) {
            defaultTableModel.addRow(new Object[]{
                index++,
                khuyenMai.getIdKM(),
                khuyenMai.getMaKM(),
                khuyenMai.getTenKM(),
                khuyenMai.getMucGiamGia(),
                khuyenMai.getDieuKien(),
                khuyenMai.getThoiGianBatDau(),
                khuyenMai.getThoiGianKetThuc(),
                (khuyenMai.getTrangThaiKM() == 0) ? "Chưa diễn ra"
                : (khuyenMai.getTrangThaiKM() == 1) ? "Đang diễn ra"
                : "Đã kết thúc",
                khuyenMai.getSoLuong()
            });
        }
    }

    private void getKhuyenMaiFromSearch() {
        defaultTableModel = (DefaultTableModel) tblDanhSachKM.getModel();
        defaultTableModel.setRowCount(0);
        ArrayList<KhuyenMai> listKM_tim_theo_ten = khuyenMaiService.searchKM(txtTimKM.getText().trim());
        int index = 1;

        // Duyệt qua danh sách kết quả tìm kiếm và thêm vào bảng
        for (KhuyenMai khuyenMai : listKM_tim_theo_ten) {
            defaultTableModel.addRow(new Object[]{
                index++,
                khuyenMai.getIdKM(),
                khuyenMai.getMaKM(),
                khuyenMai.getTenKM(),
                khuyenMai.getMucGiamGia(),
                khuyenMai.getDieuKien(),
                khuyenMai.getThoiGianBatDau(),
                khuyenMai.getThoiGianKetThuc(),
                (khuyenMai.getTrangThaiKM() == 0) ? "Chưa diễn ra"
                : (khuyenMai.getTrangThaiKM() == 1) ? "Đang diễn ra"
                : "Đã kết thúc",
                khuyenMai.getSoLuong()
            });
        }
    }

    private void getSanPham() {
        defaultTableModel = (DefaultTableModel) tblDanhSachKMDaApDung.getModel();
        defaultTableModel.setRowCount(0);
        int index = 1;
        for (KhuyenMai khuyenMai : khuyenMaiService.getListSP()) {
            defaultTableModel.addRow(new Object[]{
                index++,
                khuyenMai.getTenHD(),
                khuyenMai.getTenKM(),
                khuyenMai.getMucGiamGia(),
                khuyenMai.getDieuKien(),
                khuyenMai.getTongTien(),
                khuyenMai.getNgayThanhToan()

            });
        }
    }

//    private void getKMDaApDungFromSearch() {
//        defaultTableModel = (DefaultTableModel) tblDanhSachKMDaApDung.getModel();
//        defaultTableModel.setRowCount(0);
//        ArrayList<KhuyenMai> listKMDaApDung_tim_theo_ten = khuyenMaiService.searchKMDaApDung(txtTimKMDaApDung.getText().trim());
//        int index = 1;
//
//        // Duyệt qua danh sách kết quả tìm kiếm và thêm vào bảng
//        for (KhuyenMai khuyenMai : listKMDaApDung_tim_theo_ten) {
//            defaultTableModel.addRow(new Object[]{
//                index++,
//                khuyenMai.getIdKM(),
//                khuyenMai.getMaKM(),
//                khuyenMai.getTenKM(),
//                khuyenMai.getMucGiamGia(),
//                khuyenMai.getDieuKien(),
//                khuyenMai.getThoiGianBatDau(),
//                khuyenMai.getThoiGianKetThuc(),
//                (khuyenMai.getTrangThaiKM() == 0) ? "Chưa diễn ra"
//                : (khuyenMai.getTrangThaiKM() == 1) ? "Đang diễn ra"
//                : "Đã kết thúc",
//                khuyenMai.getSoLuong()
//            });
//        }
//    }
    private void showDetail() {
        ArrayList<KhuyenMai> listSauTim = khuyenMaiService.searchKM(txtTimKM.getText());
        KhuyenMai khuyenMai = listSauTim.get(index);
        txtMaKM.setText(khuyenMai.getMaKM());
        txtTenKM.setText(khuyenMai.getTenKM());
        txtMucGiamGia.setText(String.valueOf(khuyenMai.getMucGiamGia()));
        txtDieuKien.setText(String.valueOf(khuyenMai.getDieuKien()));
        txtBatDau.setText(khuyenMai.getThoiGianBatDau().toString());
        txtKetThuc.setText(khuyenMai.getThoiGianKetThuc().toString());
        if (khuyenMai.getTrangThaiKM() == 0) {
            cboTrangThaiKM.setSelectedIndex(0); // Chưa diễn ra
        } else if (khuyenMai.getTrangThaiKM() == 1) {
            cboTrangThaiKM.setSelectedIndex(1); // Đang diễn ra
        } else if (khuyenMai.getTrangThaiKM() == 2) {
            cboTrangThaiKM.setSelectedIndex(2); // Đã kết thúc
        }
        txtSoLuong.setText(String.valueOf(khuyenMai.getSoLuong()));
    }

    private KhuyenMai getKhuyenMaiFromInputThem() throws SQLException, ParseException { //C nay lấy dữ liệu từ text để thêm đối tượng trong list, cái mà sẽ được thêm mới sang database nhờ repository

        KhuyenMai khuyenMai = new KhuyenMai();
        String maDonVi = "KM0";
        String maHangChuc = "KM";
        String maKM;
        int soMa = khuyenMaiService.countKM() + 1;
        if (soMa < 10) {
            maKM = maDonVi + soMa;
        }else{
            maKM = maHangChuc + soMa;
        }
        khuyenMai.setMaKM(maKM);
        khuyenMai.setTenKM(txtTenKM.getText());
        khuyenMai.setMucGiamGia(Float.valueOf(txtMucGiamGia.getText()));
        khuyenMai.setDieuKien(Float.valueOf(txtDieuKien.getText()));

        SimpleDateFormat setKieuNgay = new SimpleDateFormat("yyyy-MM-dd");
        Date thoiGianBatDau = setKieuNgay.parse(txtBatDau.getText());
        Date thoiGianKetThuc = setKieuNgay.parse(txtKetThuc.getText());
        khuyenMai.setThoiGianBatDau(thoiGianBatDau);
        khuyenMai.setThoiGianKetThuc(thoiGianKetThuc);
//        if (cboTrangThaiKM.getSelectedIndex() == 0) {
//            khuyenMai.setTrangThaiKM(0); // Chưa diễn ra
//        } else if (cboTrangThaiKM.getSelectedIndex() == 1) {
//            khuyenMai.setTrangThaiKM(1); // Đang diễn ra
//        } else if (cboTrangThaiKM.getSelectedIndex() == 2) {
//            khuyenMai.setTrangThaiKM(2); // Đã kết thúc
//        }
        Date ngayHienTai = new Date();
        System.out.println(ngayHienTai);
        if (ngayHienTai.before(thoiGianBatDau)) {
            khuyenMai.setTrangThaiKM(0); // Chưa diễn ra
        } else if (ngayHienTai.after(thoiGianKetThuc)) {
            khuyenMai.setTrangThaiKM(2); // Đã kết thúc
        } else {
            khuyenMai.setTrangThaiKM(1); // Đang diễn ra
        }
        khuyenMai.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        return khuyenMai;
    }

    private KhuyenMai getKhuyenMaiFromInputSua() throws SQLException, ParseException {
        int row = tblDanhSachKM.getSelectedRow();
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setIdKM(Integer.valueOf(tblDanhSachKM.getValueAt(row, 1).toString()));
        khuyenMai.setTenKM(txtTenKM.getText());
        khuyenMai.setMucGiamGia(Float.valueOf(txtMucGiamGia.getText()));
        khuyenMai.setDieuKien(Float.valueOf(txtDieuKien.getText()));

        SimpleDateFormat setKieuNgay = new SimpleDateFormat("yyyy-MM-dd");
        Date thoiGianBatDau = setKieuNgay.parse(txtBatDau.getText());
        Date thoiGianKetThuc = setKieuNgay.parse(txtKetThuc.getText());
        khuyenMai.setThoiGianBatDau(thoiGianBatDau);
        khuyenMai.setThoiGianKetThuc(thoiGianKetThuc);
//        if (cboTrangThaiKM.getSelectedIndex() == 0) {
//            khuyenMai.setTrangThaiKM(0); // Chưa diễn ra
//        } else if (cboTrangThaiKM.getSelectedIndex() == 1) {
//            khuyenMai.setTrangThaiKM(1); // Đang diễn ra
//        } else if (cboTrangThaiKM.getSelectedIndex() == 2) {
//            khuyenMai.setTrangThaiKM(2); // Đã kết thúc
//        }
        Date ngayHienTai = new Date();
        System.out.println(ngayHienTai);
        if (ngayHienTai.before(thoiGianBatDau)) {
            khuyenMai.setTrangThaiKM(0); // Chưa diễn ra
        } else if (ngayHienTai.after(thoiGianKetThuc)) {
            khuyenMai.setTrangThaiKM(2); // Đã kết thúc
        } else {
            khuyenMai.setTrangThaiKM(1); // Đang diễn ra
        }
        khuyenMai.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        return khuyenMai;
    }

    private KhuyenMai getKhuyenMaiFromInputTTKM() throws SQLException, ParseException {
        int row = tblDanhSachKM.getSelectedRow();
        KhuyenMai khuyenMai = new KhuyenMai();

        SimpleDateFormat setKieuNgay = new SimpleDateFormat("yyyy-MM-dd");
        Date thoiGianBatDau = setKieuNgay.parse(txtBatDau.getText());
        Date thoiGianKetThuc = setKieuNgay.parse(txtKetThuc.getText());
        khuyenMai.setThoiGianBatDau(thoiGianBatDau);
        khuyenMai.setThoiGianKetThuc(thoiGianKetThuc);

        Date ngayHienTai = new Date();
        if (ngayHienTai.before(thoiGianBatDau)) {
            if (cboTrangThaiKM.getSelectedIndex() == 0) { // chưa dien ra
                khuyenMai.setTrangThaiKM(2); // Da ket thuc
            } else if (cboTrangThaiKM.getSelectedIndex() == 1) {
                khuyenMai.setTrangThaiKM(0); // chua dien ra
            } else if (cboTrangThaiKM.getSelectedIndex() == 2) {
                khuyenMai.setTrangThaiKM(0); // Đã kết thúc
            }
        } else if (ngayHienTai.after(thoiGianKetThuc)) { // da ket thuúc
            if (cboTrangThaiKM.getSelectedIndex() == 2) {
                khuyenMai.setTrangThaiKM(2); // da ket thuc
            } else if (cboTrangThaiKM.getSelectedIndex() == 1) {
                khuyenMai.setTrangThaiKM(2); // da ket thuc
            } else if (cboTrangThaiKM.getSelectedIndex() == 0) {
                khuyenMai.setTrangThaiKM(2); // da ket thuc
            }
        } else if (ngayHienTai.equals(thoiGianBatDau) || ngayHienTai.after(thoiGianBatDau)) {
            // Ngày hiện tại sau hoặc bằng ngày bắt đầu
            if (ngayHienTai.equals(thoiGianKetThuc) || ngayHienTai.before(thoiGianKetThuc)) {
                if (cboTrangThaiKM.getSelectedIndex() == 0) {
                    khuyenMai.setTrangThaiKM(1); // đang diễn ra
                } else if (cboTrangThaiKM.getSelectedIndex() == 1) {
                    khuyenMai.setTrangThaiKM(2); // da ket thúc
                } else if (cboTrangThaiKM.getSelectedIndex() == 2) {
                    khuyenMai.setTrangThaiKM(1); // dang dien ra
                }
            }
        }
        khuyenMai.setIdKM(Integer.valueOf(tblDanhSachKM.getValueAt(row, 1).toString()));
        return khuyenMai;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachKMDaApDung = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        txtTenKM = new javax.swing.JTextField();
        cboTrangThaiKM = new javax.swing.JComboBox<>();
        btnSuaKM = new javax.swing.JButton();
        btnThemKM = new javax.swing.JButton();
        btnThayDoiTTKM = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        txtMucGiamGia = new javax.swing.JTextField();
        txtMaKM = new javax.swing.JLabel();
        txtBatDau = new javax.swing.JTextField();
        txtKetThuc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachKM = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTimKM = new javax.swing.JButton();
        txtTimKM = new javax.swing.JTextField();

        setBackground(new java.awt.Color(234, 231, 214));

        jPanel1.setBackground(new java.awt.Color(234, 231, 214));

        tblDanhSachKMDaApDung.setBackground(new java.awt.Color(176, 212, 184));
        tblDanhSachKMDaApDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên HĐ", "Tên KM", "Mức Giảm", "Điều Kiện", "Tổng Tiền", "Ngày TT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDanhSachKMDaApDung);

        jPanel2.setBackground(new java.awt.Color(176, 212, 184));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Mã KM:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tên KM:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Mức Giảm Giá:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Điều Kiện:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Bắt Đầu:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Kết Thúc:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Trạng Thái:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Số Lượng:");

        lblMaHD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMaHD.setForeground(new java.awt.Color(255, 0, 0));

        cboTrangThaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa diễn ra", "Đang diễn ra", "Đã kết thúc" }));

        btnSuaKM.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSuaKM.setText("Sửa KM");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        btnThemKM.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemKM.setText("Thêm KM");
        btnThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKMActionPerformed(evt);
            }
        });

        btnThayDoiTTKM.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThayDoiTTKM.setText("Đổi TT");
        btnThayDoiTTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiTTKMActionPerformed(evt);
            }
        });

        txtMaKM.setText("?");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnThemKM)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDieuKien, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMucGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(txtBatDau, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtKetThuc, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenKM, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                .addGap(7, 7, 7)
                                .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnSuaKM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThayDoiTTKM)
                        .addGap(0, 24, Short.MAX_VALUE))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaKM, btnThayDoiTTKM, btnThemKM});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblMaHD)
                    .addComponent(txtMaKM))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaKM)
                    .addComponent(btnThemKM)
                    .addComponent(btnThayDoiTTKM))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaKM, btnThayDoiTTKM, btnThemKM});

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Khuyến Mãi");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Danh sách đã áp dụng");

        tblDanhSachKM.setBackground(new java.awt.Color(176, 212, 184));
        tblDanhSachKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID", "Mã KM", "Tên KM", "Mức Giảm", "Điều Kiện", "Bắt Đầu", "Kết Thúc", "Trạng Thái", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKMMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachKM);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Danh sách khuyến mãi");

        jPanel4.setBackground(new java.awt.Color(176, 212, 184));

        btnTimKM.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTimKM.setText("Tìm KM");
        btnTimKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTimKM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTimKM)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimKM)
                        .addGap(7, 7, 7))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKMActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (confirm == JOptionPane.OK_OPTION) {
            try {
                KhuyenMai khuyenMai = getKhuyenMaiFromInputThem();
                if (khuyenMaiService.themKM(khuyenMai) != 0) {
                    JOptionPane.showMessageDialog(this, "Add done.");
                    getKhuyenMaiFromSearch(); //Load lại list sau khi thêm để hiển thị lên bảng
                    showDetail();
                } else {
                    JOptionPane.showMessageDialog(this, "Add false.");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiJP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(KhuyenMaiJP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnThemKMActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        index = tblDanhSachKM.getSelectedRow();
//        if (txt.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter the animal name!");
//            txtAnimalName.requestFocus();
//            return;
//        }

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row you want to edit!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa khuyến mãi " + txtTenKM.getText() + " không?");
        if (confirm == JOptionPane.OK_OPTION) {
            try {
                KhuyenMai khuyenMai = getKhuyenMaiFromInputSua();
                if (khuyenMaiService.suaKM(khuyenMai) != 0) {
                    JOptionPane.showMessageDialog(this, "Edit done.");
                    getKhuyenMaiFromSearch(); // Tải lại danh sách search sau khi sửa
                    showDetail();
                } else {
                    JOptionPane.showMessageDialog(this, "Edit failed.");
                    return;
                }
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(KhuyenMaiJP.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình sửa: " + ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void tblDanhSachKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKMMouseClicked
        // TODO add your handling code here:
        if (tblDanhSachKM.getRowCount() > -1) {
            index = tblDanhSachKM.getSelectedRow();
            showDetail();
        }
    }//GEN-LAST:event_tblDanhSachKMMouseClicked

    private void btnTimKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKMActionPerformed
        // TODO add your handling code here:
        getKhuyenMaiFromSearch();

    }//GEN-LAST:event_btnTimKMActionPerformed

    private void btnThayDoiTTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiTTKMActionPerformed
        // TODO add your handling code here:
        index = tblDanhSachKM.getSelectedRow();
        System.out.println(index);
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi muốn thay đổi trạng thái!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn đổi trạng thái khuyến mãi " + txtTenKM.getText() + " không?");
        if (confirm == JOptionPane.OK_OPTION) {
            try {
                KhuyenMai khuyenMai = getKhuyenMaiFromInputTTKM();
                if (khuyenMaiService.doiTTKM(khuyenMai) != 0) {
                    JOptionPane.showMessageDialog(this, "Đổi thành công.");
                    getKhuyenMaiFromSearch(); // Tải lại danh sách sau khi sửa
                    showDetail();
                } else {
                    JOptionPane.showMessageDialog(this, "Dổi thất bại.");
                    return;
                }
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(KhuyenMaiJP.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "An error occurred while editing: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnThayDoiTTKMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnThayDoiTTKM;
    private javax.swing.JButton btnThemKM;
    private javax.swing.JButton btnTimKM;
    private javax.swing.JComboBox<String> cboTrangThaiKM;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JTable tblDanhSachKM;
    private javax.swing.JTable tblDanhSachKMDaApDung;
    private javax.swing.JTextField txtBatDau;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtKetThuc;
    private javax.swing.JLabel txtMaKM;
    private javax.swing.JTextField txtMucGiamGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTimKM;
    // End of variables declaration//GEN-END:variables

}
