package com.example.demo.mapper;

import com.example.demo.dto.PhongBanDTO;
import com.example.demo.models.PhongBan;

public class PhongBanMapper {
        public static PhongBan mapToModel(PhongBanDTO phongBanDTO){
           PhongBan phongBan = PhongBan.builder()
                    .maPB(phongBanDTO.getMaPB())
                    .moTa(phongBanDTO.getMoTa())
                    .tenPB(phongBanDTO.getTenPB())
                    .build();
           return phongBan;
        }
    public static PhongBanDTO mapToDTO(PhongBan phongBan){
        return PhongBanDTO.builder()
                .maPB(phongBan.getMaPB())
                .moTa(phongBan.getMoTa())
                .tenPB(phongBan.getTenPB())
                .build();
    }
}
