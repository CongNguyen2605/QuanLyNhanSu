package com.example.demo.mapper;

import com.example.demo.dto.ChamCongDTO;
import com.example.demo.models.ChamCong;
import com.example.demo.models.NhanVien;

public class ChamCongMapper {
    public static ChamCong mapToModel(ChamCongDTO chamCongDTO){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(chamCongDTO.getMaNV());
        ChamCong chamCong = ChamCong.builder()
                .maChamCong(chamCongDTO.getMaChamCong())
                .ngayChamCong(chamCongDTO.getNgayChamCong())
                .trangThai(chamCongDTO.getTrangThai())
                .nhanVien(nhanVien)

                .build();
        return chamCong;
    }
    public static ChamCongDTO mapToDTO(ChamCong chamCong){
        if (chamCong == null) {
            return null; // Handle null case appropriately, depending on your logic
        }
        ChamCongDTO chamCongDTO = ChamCongDTO.builder()
                .maChamCong(chamCong.getMaChamCong())
                .ngayChamCong(chamCong.getNgayChamCong())
                .trangThai(chamCong.getTrangThai())
                .maNV(chamCong.getNhanVien().getMaNV())

                .build();
        return chamCongDTO ;
    }
}
