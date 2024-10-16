package com.n2.iService;

import com.n2.iRepository.HoaDonRepository;
import com.n2.viewModel.HoaDonViewModel;
import java.sql.SQLException;
import java.util.List;

public class HoaDonService {
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    public List<HoaDonViewModel> getAllHDD() {
        return hoaDonRepository.getAllHDD();
    }
     public List<HoaDonViewModel> getHDfindByMaHD(String maHD) throws SQLException {
         return hoaDonRepository.getHDfindByMaHD(maHD);
     }
}
