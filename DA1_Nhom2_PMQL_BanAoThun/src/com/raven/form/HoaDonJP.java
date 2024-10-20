/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.itextpdf.text.pdf.PdfName;
import com.n2.domainModel.HoaDon;
import com.n2.iService.HoaDonService;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import static com.raven.form.BanHangJP.formatCurrency;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class HoaDonJP extends javax.swing.JPanel {
    HoaDonService hoaDonService = new HoaDonService();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel modelHDCT = new DefaultTableModel();
    private List<HoaDonChiTietViewModel> listHDCT = new ArrayList<>();
    /**
     * Creates new form Form_1
     */
    public HoaDonJP() {
        initComponents();
        loadHD();
    }
    public void loadHD() {
        List<HoaDonViewModel> list = hoaDonService.getAllHDD();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        int index = 1;
        for (HoaDonViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.getTongTien(), x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }
    }

    public void timHDtheoMa() throws SQLException {
        String maHDTim = txtTimKiem.getText();
        if (txtTimKiem == null) {
            loadHD();
        } else {
            List<HoaDonViewModel> list = hoaDonService.getHDfindByMaHD(maHDTim);
            model = (DefaultTableModel) tblHoaDonCho.getModel();
            model.setRowCount(0);
            int index = 1;
            for (HoaDonViewModel x : list) {
                model.addRow(new Object[]{
                    index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.getTongTien(), x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
                });
                index++;
            }
        }

    }

    public void HDTT() throws SQLException {
        HoaDon hd = new HoaDon();
        int tt = hd.getTrangThai();
        List<HoaDonViewModel> list = hoaDonService.getHDfindHDTT(tt);
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        int index = 1;
        for (HoaDonViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.getTongTien(), x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }

    }

    public void HDcTT() throws SQLException {

        List<HoaDonViewModel> list = hoaDonService.getHDfindHDCTT();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        int index = 1;
        for (HoaDonViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.getTongTien(), x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }

    }

    public void HDHuy() throws SQLException {

        List<HoaDonViewModel> list = hoaDonService.getHDfindHDCTT();
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        int index = 1;
        for (HoaDonViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.getTongTien(), x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }

    }

    public void TimKiem() throws SQLException {

      // Lấy ngày bắt đầu và kết thúc từ giao diện người dùng
    Date ngayBatDauDate = this.ngayBatDau.getDate(); // Giả sử đây là một JDatePicker hoặc tương tự
    Date ngayKetThucDate = this.ngayKetThuc.getDate();

    SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");

    try {
        String maHD = null;
        if(txtTimKiem.getText().isEmpty()){
            maHD = null;
        }else{
            maHD = txtTimKiem.getText();
        }
        int trangThai = 1;

        // Xác định trạng thái hóa đơn
        if (rdHDHTT.isSelected()) {
            trangThai = 1;
        } else if (rdHDCTT.isSelected()) {
            trangThai = 0;
        } else {
            trangThai = 2;
        }

        // Định dạng lại ngày thành chuỗi
        String formattedStartDate = targetFormat.format(ngayBatDauDate);
        String formattedEndDate = targetFormat.format(ngayKetThucDate);

        System.out.println("Start Date: " + formattedStartDate); // Kết quả: 01-10-2024
        System.out.println("End Date: " + formattedEndDate);

        // Sử dụng ngày đã định dạng trong truy vấn
        List<HoaDonViewModel> list = hoaDonService.getHoaDonsByDateRangeAndTrangThai(formattedStartDate, formattedEndDate, trangThai, maHD);

        DefaultTableModel model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);

        int index = 1;
        for (HoaDonViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.getTongTien(), x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Xử lý ngoại lệ ở đây
    }

    }

    public void clickHD() throws SQLException {

        loadHDCT();
    }

    public void loadHDCT() throws SQLException {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());
        int idHD = hoaDonService.getIdHDByMaHD(maHD);
        listHDCT = hoaDonService.getAllHDCT(idHD);
        modelHDCT = (DefaultTableModel) tblGioHang.getModel();
        modelHDCT.setRowCount(0);
        int index = 1;
        for (HoaDonChiTietViewModel x : listHDCT) {
            modelHDCT.addRow(new Object[]{
                index, x.getMaSP(), x.getTenSP(), x.getSoLuong(), x.getDonGia(), x.getTenKC(), x.getTenCL()
            });
            index++;
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        ngayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ngayKetThuc = new com.toedter.calendar.JDateChooser();
        rdHDHTT = new javax.swing.JRadioButton();
        rdHDCTT = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(234, 231, 214));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn");

        tblHoaDonCho.setBackground(new java.awt.Color(176, 212, 184));
        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên nhân viên", "Ngày tạo", "Tổng tiền", "Tổng tiền sau giảm", "Trạng thái"
            }
        ));
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonCho);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Tìm kiếm");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        tblGioHang.setBackground(new java.awt.Color(176, 212, 184));
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Kích cỡ", "Chất liệu"
            }
        ));
        jScrollPane3.setViewportView(tblGioHang);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Giỏ hàng");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Ngày bắt đầu");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Ngày kết thúc");

        rdHDHTT.setText("Đã thanh toán");
        rdHDHTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHDHTTActionPerformed(evt);
            }
        });

        rdHDCTT.setText("Chưa thanh toán");
        rdHDCTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHDCTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(407, 407, 407)
                        .addComponent(btnLamMoi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTim))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdHDHTT)
                            .addComponent(rdHDCTT))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTim))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(rdHDHTT)
                        .addGap(9, 9, 9)
                        .addComponent(rdHDCTT))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
    try {
            clickHD();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        try {
            // TODO add your handling code here:
            TimKiem();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        loadHD();
        txtTimKiem.setText(" ");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void rdHDHTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHDHTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdHDHTTActionPerformed

    private void rdHDCTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHDCTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdHDCTTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser ngayBatDau;
    private com.toedter.calendar.JDateChooser ngayKetThuc;
    private javax.swing.JRadioButton rdHDCTT;
    private javax.swing.JRadioButton rdHDHTT;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
