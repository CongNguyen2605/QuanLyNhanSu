package com.example.demo.service;

import com.example.demo.dto.PhongBanDTO;
import com.example.demo.models.PhongBan;

import java.util.List;

public interface PhongBanService {
    List<PhongBanDTO> findPhongBan();


    PhongBan addPhongBan(PhongBanDTO phongBanDTO);

    PhongBanDTO findID(Long maPB);

    void updatePB(PhongBanDTO phongBanDTO);

    void deletePhongBan(Long maPB);
    List<PhongBanDTO> searchPhongBan(String query);
}
