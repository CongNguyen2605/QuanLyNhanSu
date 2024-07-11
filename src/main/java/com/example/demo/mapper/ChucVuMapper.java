package com.example.demo.mapper;

import com.example.demo.dto.ChucVuDTO;
import com.example.demo.models.ChucVu;

public class ChucVuMapper {
    public static ChucVu mapToModel(ChucVuDTO chucVuDTO){
        return ChucVu.builder()
                .maChucVu(chucVuDTO.getMaChucVu())
                .moTa(chucVuDTO.getMoTa())
                .tenChucVu(chucVuDTO.getTenChucVu())
                .build();
    }

    public static ChucVuDTO mapToDTO(ChucVu chucVu){
        return ChucVuDTO.builder()
                .maChucVu(chucVu.getMaChucVu())
                .moTa(chucVu.getMoTa())
                .tenChucVu(chucVu.getTenChucVu())
                .build();
    }


}
