package com.example.demo.service;

import com.example.demo.dto.ChucVuDTO;
import com.example.demo.models.ChucVu;

import java.util.List;

public interface ChucVuService {
    List<ChucVuDTO> findChucVu();


    ChucVu addChucVu(ChucVuDTO chucVuDTO);

    ChucVuDTO findID(Long maChucVu);

    void updateChucVu(ChucVuDTO chucVuDTO);

    void deleteChucVu(Long maChucVu);
}
