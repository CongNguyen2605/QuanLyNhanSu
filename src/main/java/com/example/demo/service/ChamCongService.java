package com.example.demo.service;

import com.example.demo.dto.ChamCongDTO;
import com.example.demo.models.ChamCong;

import java.time.LocalDate;
import java.util.List;

public interface ChamCongService {
    List<ChamCongDTO> findAllChamCong();



    void updateChamCongList(List<ChamCongDTO> chamCongDTOs, String ngay);


    ChamCongDTO findByNhanVienAndNgay(Long maNV, String ngay);

    List<ChamCong> findChamCongByNgayChamCong(LocalDate ngayChamCong);

}
