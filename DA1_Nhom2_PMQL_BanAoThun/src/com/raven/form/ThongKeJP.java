/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import DTOSanPhamThongKe.SanPhamDTO;
import com.n2.domainModel.DoanhThu;
import com.n2.iService.ThongKeService;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author RAVEN
 */
public class ThongKeJP extends javax.swing.JPanel {

    ThongKeService thongKeService = new ThongKeService();
    JFreeChart chart;
    CategoryDataset dataset;
    ChartPanel chartPanel;

    public ThongKeJP() throws ParseException {
        initComponents();
        loadInit();

    }

    public void loadInit() throws ParseException {
        loadInitDoanhThu();
        intiChar(LocalDate.now().toString(), LocalDate.now().toString());
        loadInitTable();

    }

    public void intiChar(String bt, String kt) {

        dataset = createDataset(bt, kt);
        // Kích thước của JScrollPane
        chart = ChartFactory.createBarChart3D(
                "Doanh Thu Theo Ngày", // Tên biểu đồ
                "Tháng", // Trục X
                "Doanh Thu(VND)", // Trục Y
                dataset // Dữ liệu
        );
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(pnChart.getWidth(), 250));

        // Xóa các thành phần hiện tại (nếu có) và thêm biểu đồ vào pnChart
        pnChart.removeAll();
        pnChart.setLayout(new CardLayout());// Xóa mọi thành phần cũ
        pnChart.add(chartPanel); // Thêm biểu đồ vào giữa pnChart

        // Cập nhật lại layout
        pnChart.revalidate(); // Xác thực lại layout
        pnChart.repaint(); // Vẽ lại panel

    }

    public void updateDataset(String bt, String kt) {
        // Cập nhật dataset với dữ liệu mới
        dataset = createDataset(bt, kt);
        chart.getCategoryPlot().setDataset(dataset);
        chartPanel.revalidate();
        chartPanel.repaint();
        pnChart.revalidate();
        pnChart.repaint();
    }

    private CategoryDataset createDataset(String bt, String kt) {
        // Tạo một tập dữ liệu cho biểu đồ cột
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<DoanhThu> list = thongKeService.getOneDate(bt, kt);
        System.out.println(list.size());
        dataset.addValue(1900, "Doanh Thu", "dt");
        for (DoanhThu dt : list) {
            dataset.addValue(dt.getTongDoanhThu(), "Doanh Thu", dt.getDate());
        }

        return dataset;
    }

    public void loadInitDoanhThu() throws ParseException {
        String bt = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-1";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(bt);
        String kt = LocalDate.now().toString();
        dateBatDau.setDate(date);
        dateKetThuc.setDate(new Date());
        loadDoanhThu(bt, kt);

    }

    public void loadInitTable() throws ParseException {
        String bt = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-1";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(bt);
        String kt = LocalDate.now().toString();

        dateBatDauSP.setDate(date);
        dateKetThucSP.setDate(new Date());
        loadTable(bt, kt);

    }

    public void loadDoanhThu(String bt, String kt) {
        lbDoanhThu.setText(thongKeService.getByMAndY(bt, kt).getTongDoanhThu() + " VND");

    }

    public void loadTable(String bt, String kt) {
        List<SanPhamDTO> list = thongKeService.getByDate(bt, kt);

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (SanPhamDTO spdto : list) {
            model.addRow(new Object[]{spdto.getMa(), spdto.getTen(), spdto.getSoLuong(), spdto.getDoanhThu()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnChart = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lbDoanhThu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThongKe1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tfTim = new javax.swing.JTextField();
        btnTimkiem = new javax.swing.JButton();
        dateBatDau = new com.toedter.calendar.JDateChooser();
        dateKetThuc = new com.toedter.calendar.JDateChooser();
        dateBatDauSP = new com.toedter.calendar.JDateChooser();
        dateKetThucSP = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(234, 231, 214));

        javax.swing.GroupLayout pnChartLayout = new javax.swing.GroupLayout(pnChart);
        pnChart.setLayout(pnChartLayout);
        pnChartLayout.setHorizontalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnChartLayout.setVerticalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng bán ra", "Doanh thu"
            }
        ));
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Doanh Thu:");

        lbDoanhThu.setText("NaN");

        jLabel2.setText("Ngày bắt đầu");

        jLabel3.setText("Ngày kết thúc");

        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Thống kê sản phẩm");

        jLabel5.setText("Ngày bắt đầu");

        jLabel6.setText("Ngày kết thúc");

        btnThongKe1.setText("Thống kê");
        btnThongKe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKe1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        dateBatDau.setDateFormatString("dd-MM-yyyy");

        dateKetThuc.setDateFormatString("dd-MM-yy");

        dateBatDauSP.setDateFormatString("dd-MM-yy");

        dateKetThucSP.setDateFormatString("dd-MM-yy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnChart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(23, 23, 23))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addComponent(btnTimkiem))
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateBatDauSP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(dateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(dateKetThucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(29, 29, 29)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnThongKe)
                                .addComponent(btnThongKe1))
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbDoanhThu))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThongKe)
                                .addComponent(jButton1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel2)
                                .addComponent(dateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimkiem)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThongKe1)
                            .addComponent(jButton2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(dateBatDauSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateKetThucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        Date dateBD = dateBatDau.getDate();
        Date dateKT = dateKetThuc.getDate();
        if (dateBD == null || dateKT == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ ngày tháng");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        // Chuyển đổi đối tượng Date sang chuỗi định dạng yyyy-MM-dd
        String bt = sdf.format(dateBD);
        String kt = sdf.format(dateKT);
        loadDoanhThu(bt, kt);
        updateDataset(bt, kt);

    }//GEN-LAST:event_btnThongKeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            loadInitDoanhThu();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThongKe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKe1ActionPerformed
        Date dateBD = dateBatDauSP.getDate();
        Date dateKT = dateKetThucSP.getDate();
        if (dateBD == null || dateKT == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ ngày tháng");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        // Chuyển đổi đối tượng Date sang chuỗi định dạng yyyy-MM-dd
        String bt = sdf.format(dateBD);
        String kt = sdf.format(dateKT);
        loadTable(bt, kt);
    }//GEN-LAST:event_btnThongKe1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            loadInitTable();
            tfTim.setText(null);
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        String text = tfTim.getText().trim();
        if (text == null || text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nội dung!");
            return;
        }
        Date dateBD = dateBatDauSP.getDate();
        Date dateKT = dateKetThucSP.getDate();
        if (dateBD == null || dateKT == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ ngày tháng");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        // Chuyển đổi đối tượng Date sang chuỗi định dạng yyyy-MM-dd
        String bt = sdf.format(dateBD);
        String kt = sdf.format(dateKT);
        List<SanPhamDTO> list = thongKeService.searchByDate(bt, kt, text);

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (SanPhamDTO spdto : list) {
            model.addRow(new Object[]{spdto.getMa(), spdto.getTen(), spdto.getSoLuong(), spdto.getDoanhThu()});
        }
    }//GEN-LAST:event_btnTimkiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnThongKe1;
    private javax.swing.JButton btnTimkiem;
    private com.toedter.calendar.JDateChooser dateBatDau;
    private com.toedter.calendar.JDateChooser dateBatDauSP;
    private com.toedter.calendar.JDateChooser dateKetThuc;
    private com.toedter.calendar.JDateChooser dateKetThucSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDoanhThu;
    private javax.swing.JPanel pnChart;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField tfTim;
    // End of variables declaration//GEN-END:variables
}
