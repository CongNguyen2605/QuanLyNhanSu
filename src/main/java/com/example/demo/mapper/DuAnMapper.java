// Mapper
package com.example.demo.mapper;

import com.example.demo.dto.DuAnDTO;
import com.example.demo.models.DuAn;
import com.example.demo.models.NhanVien;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DuAnMapper {
    public static DuAn mapToModel(DuAnDTO duAnDTO) {
        Set<NhanVien> nhanViens = new HashSet<>();
              if(duAnDTO.getMaNV() != null)
                 nhanViens = duAnDTO.getMaNV().stream()
                .map(maNV -> {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNV(maNV);
                    return nhanVien;
                })
                .collect(Collectors.toSet());

        return DuAn.builder()
                .maDA(duAnDTO.getMaDA())
                .tenDA(duAnDTO.getTenDA())
                .moTa(duAnDTO.getMoTa())
                .nhanVien(nhanViens)
                .build();
    }

    public static DuAnDTO mapToDTO(DuAn duAn) {
        Set<Long> maNV = duAn.getNhanVien().stream()
                .map(NhanVien::getMaNV)
                .collect(Collectors.toSet());

        return DuAnDTO.builder()
                .maDA(duAn.getMaDA())
                .tenDA(duAn.getTenDA())
                .moTa(duAn.getMoTa())
                .maNV(maNV)
                .build();
    }
}
