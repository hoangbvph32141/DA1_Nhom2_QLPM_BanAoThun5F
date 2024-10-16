/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.itextpdf.text.pdf.PdfName;
import com.n2.iService.HoaDonService;
import com.n2.viewModel.HoaDonViewModel;
import java.sql.SQLException;
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
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(),x.getTongTien(),x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }
    }
     public void timHDtheoMa() throws SQLException {
         String maHDTim = txtTimKiem.getText();
         if(txtTimKiem == null){
             loadHD();
         } else{
              List<HoaDonViewModel> list = hoaDonService.getHDfindByMaHD(maHDTim);
        model = (DefaultTableModel) tblHoaDonCho.getModel();
        model.setRowCount(0);
        int index = 1;
        for (HoaDonViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(),x.getTongTien(),x.getDonGiaSauGiam(), x.tt(x.getTrangThai())
            });
            index++;
        }
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
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnTim))
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTim))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        try {
            // TODO add your handling code here:
            timHDtheoMa();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        loadHD();
        txtTimKiem.setText(" ");
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
