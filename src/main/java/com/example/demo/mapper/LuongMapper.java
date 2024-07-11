package com.example.demo.mapper;

import com.example.demo.dto.LuongDTO;
import com.example.demo.models.Luong;
import com.example.demo.models.NhanVien;

public class LuongMapper {
    public static Luong mapToModel(LuongDTO luongDTO){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(luongDTO.getMaNV());
        Luong luong = Luong.builder()
                .maLuong(luongDTO.getMaLuong())
                .soTien(luongDTO.getSoTien())
                .nhanVien(nhanVien)

                .build();
        return luong;

    }
    public static LuongDTO mapToDTO(Luong luong){

        LuongDTO luongDTO = LuongDTO.builder()
                .maLuong(luong.getMaLuong())
                .soTien(luong.getSoTien())
                .maNV(luong.getNhanVien().getMaNV())

                .build();
        return luongDTO;

    }
}
