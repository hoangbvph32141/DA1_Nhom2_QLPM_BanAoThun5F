package com.raven.form;

import com.n2.domainModel.HoaDon;
import com.n2.domainModel.HoaDonChiTiet;
import com.n2.domainModel.KhuyenMai;
import com.n2.domainModel.SanPhamChiTiet;
import com.n2.iService.BanHangService;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import java.text.SimpleDateFormat;




/**
 *
 * @author RAVEN
 */
public class BanHangJP extends javax.swing.JPanel {

    BanHangService banHangService = new BanHangService();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel modelHD = new DefaultTableModel();
    private DefaultTableModel modelHDCT = new DefaultTableModel();
    private List<HoaDonChiTietViewModel> listHDCT = new ArrayList<>();
    private List<SanPhamChiTiet> listSP = new ArrayList<>();
    private List<HoaDon> listHD = new ArrayList<>();
    DecimalFormat fomat = new DecimalFormat("###,###,###");

    public BanHangJP() {
        initComponents();
        loadHD();
        loadSP();
    }

    public void loadSP() {
        List<SanPhamChiTietViewModel> list = banHangService.getAllSPCT();
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        int index = 1;
        for (SanPhamChiTietViewModel x : list) {
            model.addRow(new Object[]{
                index, x.getMaSPCT(), x.getTenSP(), x.getSoLuongTon(), x.getDonGia(), x.getTenKC(), x.getTenCL(), x.getTenMS(), x.trangThai(x.getTrangThaiSPCT())
            });
            index++;
        }
    }

    public void loadHD() {
        List<HoaDonViewModel> list = banHangService.getAllHDD();
        modelHD = (DefaultTableModel) tblHoaDonCho.getModel();
        modelHD.setRowCount(0);
        int index = 1;
        for (HoaDonViewModel x : list) {
            modelHD.addRow(new Object[]{
                index, x.getMaHD(), x.getTenNV(), x.getNgayTao(), x.tt(x.getTrangThai())
            });
            index++;
        }
    }

    public void loadHDCT() {
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

    public void insertHD() throws SQLException {
        HoaDon hd = new HoaDon();
        String ma = "HD0";
        int soMa = banHangService.countHD() + 1;
        String maHD = ma + soMa;
        hd.setMaHD(maHD);
        hd.setNgayTao(new Date());
        hd.setIDNV(1);
        hd.setTrangThai(0);
        hd.setIDKH(1);
        banHangService.insertHD(hd);
        loadHD();
    }

    public void themSPVaoGioHang() throws SQLException {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        Integer row = tblSanPham.getSelectedRow();
        int soLuongSP = Integer.parseInt(tblSanPham.getValueAt(row, 3).toString());
        float donGiaSP = Float.valueOf(tblSanPham.getValueAt(row, 4).toString());
        String maSPCT = String.valueOf(tblSanPham.getValueAt(row, 1).toString());
        String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());

        int idHD = banHangService.getIdHDByMaHD(maHD);
        int idSP = banHangService.getIdSPCTByMaSPCT(maSPCT);

        String ma = "HDCT0";
        int soMa = banHangService.countHDCT() + 1;
        String maHDCTThemVao = ma + soMa;

        HoaDonChiTiet hdct = new HoaDonChiTiet();

        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        } else {
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
            } else {
                String soLuong = JOptionPane.showInputDialog("Moi ban nhap so luong");
                if (soLuong != null) {
                    if (!soLuong.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng");

                    } else if (Integer.parseInt(soLuong) > soLuongSP) {
                        JOptionPane.showMessageDialog(this, "Không đủ số lượng");
                    } else {
                        boolean trung = false;
                        for (HoaDonChiTietViewModel x : listHDCT) {
                            if (x.getMaSP().contains(maSPCT)) {
                                trung = true;

                            }
                        }
                        if (trung) {
                            JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong giỏ hàng");
                        } else {
                            int soLuongChon = Integer.parseInt(soLuong);
                            hdct.setSoLuong(soLuongChon);
                            hdct.setDonGia(donGiaSP);
                            hdct.setIdSPCT(idSP);
                            hdct.setIdHD(idHD);
                            hdct.setMaHDCT(maHDCTThemVao);

                            int soLuongConLai = soLuongSP - soLuongChon;
                            SanPhamChiTiet spct = new SanPhamChiTiet();

                            spct.setSoLuongTon(soLuongConLai);
                            spct.setID(idSP);

                            banHangService.updateSPCT(spct);
                            loadSP();

                            banHangService.insertHDCT(hdct);
                        }

                    }
                }
            }
        }
        loadHDCT();
        loadSP();

    }

    public static String formatCurrency(float amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.'); // Dấu chấm để phân cách hàng nghìn
        symbols.setDecimalSeparator(','); // Dấu phẩy cho phần thập phân nếu cần
        DecimalFormat formatter = new DecimalFormat("#,###.##", symbols); // Định dạng cho tiền tệ

        return formatter.format(amount) + " VND"; // Thêm " VND"
    }

    public void clickHD() throws SQLException {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        Integer row = tblSanPham.getSelectedRow();
        String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());
        int idHD = banHangService.getIdHDByMaHD(maHD);
        listHDCT = banHangService.getAllHDCT(idHD);
        lblMaHD.setText(maHD);

        float mucGiam = 0;
        String lblMucGiamText = lblMucGiam.getText();
        lblMucGiamText = lblMucGiamText.replace("%", "").trim(); // Xóa ký tự '%' và loại bỏ khoảng trắng thừa

        // Kiểm tra xem chuỗi có hợp lệ hay không
        if (lblMucGiamText == null || lblMucGiamText.trim().isEmpty() || lblMucGiamText.equals("?")) {
            mucGiam = 0; // Gán giá trị mặc định
        } else {
            try {
                mucGiam = Float.valueOf(lblMucGiamText); // Chuyển đổi nếu hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không hợp lệ: " + lblMucGiamText);
                mucGiam = 0; // Hoặc xử lý theo cách khác
            }
        }

        float tongTien = 0;
        for (HoaDonChiTietViewModel x : listHDCT) {
            tongTien += x.getSoLuong() * x.getDonGia();
            System.out.println(tongTien);
        }

        float soTienDuocGiam = tongTien * (mucGiam / 100);
        System.out.println("Tổng tiền: " + tongTien);

        lblTongTien.setText(formatCurrency(tongTien));
        lblTongTienSau.setText(formatCurrency(tongTien - soTienDuocGiam));

        loadHDCT();
    }

    public void deleteSPCT() throws SQLException {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        Integer row = tblGioHang.getSelectedRow();
        String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());
        String maSPCT = String.valueOf(tblGioHang.getValueAt(row, 1).toString());
        int idHD = banHangService.getIdHDByMaHD(maHD);
        int idSPCT = banHangService.getIdSPCTByMaSPCT(maSPCT);
        System.out.println(idHD);
        System.out.println(idSPCT);
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setIdHD(idHD);
        hdct.setIdSPCT(idSPCT);
        banHangService.deleteSPCT(hdct);
    }

    public void huyHoaDon() throws SQLException {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());
        int idHD = banHangService.getIdHDByMaHD(maHD);
        banHangService.huyHD(String.valueOf(idHD));
        loadHD();
    }

    public void updateHDCT() throws SQLException {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        Integer row = tblGioHang.getSelectedRow();
        String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());
        String maSPCT = String.valueOf(tblGioHang.getValueAt(row, 1).toString());
        int idHD = banHangService.getIdHDByMaHD(maHD);
        int idSP = banHangService.getIdSPCTByMaSPCT(maSPCT);

        // Kiểm tra xem có sản phẩm nào được chọn trong tblSanPham không
        Integer rowSLSP = tblGioHang.getSelectedRow();
        if (rowSLSP < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm trong bảng sản phẩm.");
            return; // Ngừng thực hiện phương thức
        }

        // Tiếp tục với các phép toán
        int SLtruocUpdate = Integer.valueOf(tblGioHang.getValueAt(row, 3).toString());
        int idSPCT = banHangService.getIdSPCTByMaSPCT(maSPCT);

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        String soLuongMoi = JOptionPane.showInputDialog("Mời bạn nhập số lượng");
        hdct.setIdHD(idHD);
        hdct.setIdSPCT(idSPCT);
        hdct.setSoLuong(Integer.parseInt(soLuongMoi));

        String soLuongSPStr = String.valueOf(tblSanPham.getValueAt(rowSLSP, 3).toString());
        int soLuongSP = Integer.parseInt(soLuongSPStr);

        int soLuongSPSAUTT = Integer.parseInt(soLuongMoi) - SLtruocUpdate;
        int soLuongSPCTsau = soLuongSP - soLuongSPSAUTT;
        System.out.println("sau " + soLuongSPSAUTT);
        System.out.println("sausau" + soLuongSPCTsau);
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setSoLuongTon(soLuongSPCTsau);
        spct.setID(idSPCT);
        // Cập nhật hóa đơn chi tiết
        banHangService.updateHoaDonCT(hdct);
        banHangService.updateSPCT(spct);
        // Cập nhật lại bảng sản phẩm
        clickHD();
        System.out.println("Số lượng mới: " + soLuongMoi);
    }

    public void thanhToan() throws SQLException {
    Integer rowHD = tblHoaDonCho.getSelectedRow();
    if (rowHD == -1) {
        System.out.println("Chưa chọn hóa đơn nào.");
        return;
    }

    String maHD = String.valueOf(tblHoaDonCho.getValueAt(rowHD, 1).toString());
    int idHD = banHangService.getIdHDByMaHD(maHD);

    // Lấy giá trị tổng tiền và đơn giá sau giảm từ nhãn, xóa dấu phân cách hàng nghìn và "VND"
    String tongTien = lblTongTien.getText().replace(".", "").replace(" VND", "").trim(); // Loại bỏ dấu chấm và "VND"
    String donGiaSauGiam = lblTongTienSau.getText().replace(".", "").replace(" VND", "").trim(); // Loại bỏ dấu chấm và "VND"

    // Kiểm tra xem chuỗi có hợp lệ hay không
    if (!isNumeric(tongTien) || donGiaSauGiam.equals("?") || donGiaSauGiam.isEmpty()) {
        System.out.println("Lỗi định dạng số: Giá trị không hợp lệ (" + tongTien + ", " + donGiaSauGiam + ")");
        return;
    }
    try {
        // Chuyển đổi chuỗi thành kiểu float
        float tongTienValue = Float.parseFloat(tongTien);
        float donGiaSauGiamValue = Float.parseFloat(donGiaSauGiam);
        String maKH = null;
        int id = 1;
        if (txtKM.getText() == null || txtKM.getText().isEmpty()) {
            id = 1;
        } else {
            maKH = txtKM.getText();
            id = banHangService.getIdKMbyMaKM(maKH);
        }

        
Date now = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
String formattedDate = sdf.format(now);

        HoaDon hd = new HoaDon();
        hd.setIDKM(id); // Giả định không có khuyến mãi
        hd.setTongTien(tongTienValue);
        hd.setDonGiaSauGiam(donGiaSauGiamValue);
        hd.setID(idHD);
        hd.setNgayThanhToan(now);

        // Gọi dịch vụ để thực hiện thanh toán
        banHangService.thanhToan(hd);

        // Tải lại bảng hóa đơn sau khi thanh toán thành công
        loadHD();

        // Log tổng tiền mà không có đuôi "VND"
        System.out.println("Tổng tiền: " + formatCurrency(tongTienValue).replace(" VND", ""));

        // Generate PDF invoice
          String filePath = "src/bills/bill.pdf";

    try {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        // Add the invoice content to the PDF document
        document.add(new Paragraph("F5 Shirt", titleFont));
        document.add(new Paragraph("Hoa don thanh toan", titleFont));
        document.add(new Paragraph("---------------------------------------------------", contentFont));
        document.add(new Paragraph("Ngay thanh toan    " + formattedDate, contentFont));
        document.add(new Paragraph("Ten khach hang:    " + hd.getIDKH(), contentFont));
        document.add(new Paragraph("---------------------------------------------------", contentFont));
        document.add(new Paragraph("", contentFont));

        float[] columnWidths = {3f, 1.5f, 1.5f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(40);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(new PdfPCell(new Phrase("Ten san pham", contentFont)));
        table.addCell(new PdfPCell(new Phrase("So luong", contentFont)));
        table.addCell(new PdfPCell(new Phrase("Don gia", contentFont)));

        for (HoaDonChiTietViewModel x : listHDCT) {
            table.addCell(new PdfPCell(new Phrase(x.getTenSP(), contentFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(x.getSoLuong()), contentFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(x.getDonGia()), contentFont)));
        }

        document.add(table);
        document.add(new Paragraph("---------------------------------------------------", contentFont));
        document.add(new Paragraph("Tong tien:              " + tongTienValue+ "   VND", contentFont));
//        document.add(new Paragraph("khuyen mai:          " + mucGiam, contentFont));
        document.add(new Paragraph("Thanh tien:            " + donGiaSauGiamValue + "   VND", contentFont));

            document.add(new Paragraph("Tien khach dua:     " + txTienKhachDua.getText() + "   VND" , contentFont));
            document.add(new Paragraph("Tien thua:             " + lblTienThua.getText(), contentFont));

        document.add(new Paragraph("---------------------------------------------------", contentFont));
        document.add(new Paragraph("              Cam on quy khach", contentFont));

        document.close();
        System.out.println("Invoice PDF generated successfully at: " + filePath);
    } catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
    }
    
    } catch (NumberFormatException e) {
        System.out.println("Lỗi định dạng số: " + e.getMessage());
    }
    txtKM.setText(" ");
    txTienKhachDua.setText(" ");
    lblTenKM.setText("?");
    lblMucGiam.setText("?");
    lblTongTien.setText("?");
    lblTienThua.setText("?");
    lblTongTienSau.setText("?");
    lblMaHD.setText("?");
}


// Phương thức kiểm tra xem chuỗi có phải là số không
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false; // Chuỗi rỗng không phải là số
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false; // Nếu có ký tự không phải số hoặc dấu chấm
            }
        }
        return true; // Nếu tất cả ký tự là số hoặc dấu chấm
    }

    public void getKM() {
        Integer rowHD = tblHoaDonCho.getSelectedRow();
        if (rowHD == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đớn");
            return;
        }
        KhuyenMai km = banHangService.getKhuyenMaiByMaKH(txtKM.getText());
        lblTenKM.setText(km.getTenKM());
        lblMucGiam.setText(String.valueOf(km.getMucGiamGia()) + "%");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        txTienKhachDua = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();
        txMaKH = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        lblTongTienSau = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        taraGhiChu = new javax.swing.JTextArea();
        btnHuyHoaDon = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtKM = new javax.swing.JTextField();
        btnTimKM = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lblTenKM = new javax.swing.JLabel();
        lblMucGiam = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnXoaSP = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnTruoc = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnThemSP = new javax.swing.JButton();
        btnUpdateSP = new javax.swing.JButton();

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setText("Tạo hóa đơn");

        setBackground(new java.awt.Color(234, 231, 214));

        tblHoaDonCho.setBackground(new java.awt.Color(176, 212, 184));
        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên nhân viên", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonCho);

        tblSanPham.setBackground(new java.awt.Color(176, 212, 184));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "SL còn lại", "Giá", "Kích cỡ", "Chất liệu", "Màu sắc", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tblSanPham);

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

        jPanel1.setBackground(new java.awt.Color(176, 212, 184));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Mã HĐ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Mã KH");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Tổng tiền");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tiền khách đưa");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Tiền thừa");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Khuyến mãi");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Tổng tiền sau");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Ghi chú");

        lblMaHD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMaHD.setForeground(new java.awt.Color(255, 0, 0));
        lblMaHD.setText("?");

        txTienKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txTienKhachDuaMouseClicked(evt);
            }
        });
        txTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTienKhachDuaActionPerformed(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setText("?");

        txMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaKHActionPerformed(evt);
            }
        });

        lblTienThua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 0, 0));
        lblTienThua.setText("?");

        lblTongTienSau.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTongTienSau.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTienSau.setText("?");

        taraGhiChu.setColumns(20);
        taraGhiChu.setRows(5);
        jScrollPane4.setViewportView(taraGhiChu);

        btnHuyHoaDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKMActionPerformed(evt);
            }
        });

        btnTimKM.setText("Tim");
        btnTimKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKMActionPerformed(evt);
            }
        });

        lblTenKM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTenKM.setForeground(new java.awt.Color(0, 0, 255));
        lblTenKM.setText("?");

        lblMucGiam.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMucGiam.setForeground(new java.awt.Color(0, 0, 255));
        lblMucGiam.setText("?");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Tên KM");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Mức giảm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblTongTienSau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhToan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtKM, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txMaKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                            .addComponent(txTienKhachDua, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMaHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblMucGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblMaHD))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTongTien))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTienThua))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKM))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKM)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMucGiam)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblTongTienSau))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDon)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(btnThanhToan))
                .addGap(43, 43, 43))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn chờ");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Giỏ hàng");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Sản phẩm");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Hóa đơn");

        btnXoaSP.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXoaSP.setText("Xóa SP");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton6.setText("Sau");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnTruoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTruoc.setText("Trước");
        btnTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruocActionPerformed(evt);
            }
        });

        jLabel17.setText("1");

        jLabel18.setText("/");

        jLabel19.setText("9");

        btnThemSP.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemSP.setText("Thêm SP");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnUpdateSP.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUpdateSP.setText("Sửa SP");
        btnUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnThemSP)
                                        .addGap(35, 35, 35))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnUpdateSP)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnXoaSP))))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btnTruoc)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(167, 167, 167)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaSP)
                            .addComponent(btnUpdateSP))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(btnThemSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(btnTruoc)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTienKhachDuaActionPerformed

        String donGiaSauGiam = lblTongTienSau.getText().replace(".", "").replace(" VND", "").trim(); // Loại bỏ dấu chấm và "VND"
        float donGiaSauGiamValue = Float.parseFloat(donGiaSauGiam);
        Float tienKhachDua = Float.valueOf(txTienKhachDua.getText());
        Float tienThua = tienKhachDua - donGiaSauGiamValue;
        Float tienThieu = donGiaSauGiamValue - tienKhachDua;
        String tienThieuVND = String.valueOf(formatCurrency(tienThieu));

        if (tienThua < 0) {
            JOptionPane.showMessageDialog(this, "Tien khach dua con thieu " + tienThieuVND);
        } else {
            lblTienThua.setText(String.valueOf(formatCurrency(tienThua)));
        }

        System.out.println(tienThua);

    }//GEN-LAST:event_txTienKhachDuaActionPerformed

    private void txMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaKHActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        try {
            // TODO add your handling code here:
            Integer rowHD = tblHoaDonCho.getSelectedRow();
            if(rowHD < 0 ){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn hủy");
                
                return;
            }
            huyHoaDon();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTruocActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        try {
            // TODO add your handling code here:]
            insertHD();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadHD();


    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        try {

            clickHD();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        try {
            Integer rowHD = tblHoaDonCho.getSelectedRow();
            Integer rowSP = tblSanPham.getSelectedRow();
            
              if (rowHD < 0 || rowSP < 0 ) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn và sản phẩm");
                return;
            }
            themSPVaoGioHang();
            clickHD();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        try {
            // TODO add your handling code here:
            Integer rowGH = tblGioHang.getSelectedRow();
            if(rowGH < 0 ){
                JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để xóa");
                return;
            }
            deleteSPCT();
            clickHD();
            loadSP();
            loadHDCT();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSPActionPerformed
        try {
            Integer rowHD = tblHoaDonCho.getSelectedRow();
            if (rowHD < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn.");

                return;
            }
            // TODO add your handling code here:          
            updateHDCT();
            loadSP();

        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateSPActionPerformed

    private void txTienKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txTienKhachDuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txTienKhachDuaMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        try {
            // TODO add your handling code here:
            String checkTien = txTienKhachDua.getText();
            if(checkTien.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền khách đưa.");
            }
            thanhToan();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTimKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKMActionPerformed
        // TODO add your handling code here:

        try {
            getKM();
            clickHD();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTimKMActionPerformed

    private void txtKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnTimKM;
    private javax.swing.JButton btnTruoc;
    private javax.swing.JButton btnUpdateSP;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMucGiam;
    private javax.swing.JLabel lblTenKM;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienSau;
    private javax.swing.JTextArea taraGhiChu;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txMaKH;
    private javax.swing.JTextField txTienKhachDua;
    private javax.swing.JTextField txtKM;
    // End of variables declaration//GEN-END:variables

}
