/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.n2.repository;

import com.n2.domainModel.DoanhThu;
import java.util.List;

/**
 *
 * @author admin
 */
public interface iThongKeRepository {
    
    DoanhThu getDoanhThuByThang(String m, String y);
}
