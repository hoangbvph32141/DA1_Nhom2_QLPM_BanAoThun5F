package com.n2.iService;

import com.n2.iRepository.HoaDonRepository;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class HoaDonService {
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    public List<HoaDonViewModel> getAllHDD() {
        return hoaDonRepository.getAllHDD();
    }

    public List<HoaDonViewModel> getHDfindByMaHD(String maHD) throws SQLException {
        return hoaDonRepository.getHDfindByMaHD(maHD);
    }

    public List<HoaDonViewModel> getHDfindHDTT(int tt) throws SQLException {
        return hoaDonRepository.getHDfindHDTT(tt);
    }

    public List<HoaDonViewModel> getHDfindHDCTT() throws SQLException {
        return hoaDonRepository.getHDfindHDCTT();
    }

    public List<HoaDonViewModel> getHDfindHDHuy() throws SQLException {
        return hoaDonRepository.getHDfindHDHuy();
    }

    public int getIdHDByMaHD(String maHD) throws SQLException {
        return hoaDonRepository.getIdHDByMaHD(maHD);
    }

    public int getIdSPCTByMaSPCT(String maSPCT) throws SQLException {
        return hoaDonRepository.getIdSPCTByMaSPCT(maSPCT);
    }

         public List<HoaDonChiTietViewModel> getAllHDCT(int id) {
             return hoaDonRepository.getAllHDCT(id);
         }
         
          public List<HoaDonViewModel> getHoaDonsByDateRangeAndTrangThai(String startDate, String endDate, int trangThai, String maHD) throws SQLException, ParseException{
              return hoaDonRepository.getHoaDonsByDateRangeAndTrangThai(startDate, endDate, trangThai, maHD);
          }
          
}
