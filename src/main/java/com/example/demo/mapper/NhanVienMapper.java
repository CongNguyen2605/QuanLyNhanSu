package com.example.demo.mapper;

import com.example.demo.dto.DuAnDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.models.ChucVu;
import com.example.demo.models.DuAn;
import com.example.demo.models.NhanVien;
import com.example.demo.models.PhongBan;

import java.util.Set;
import java.util.stream.Collectors;

public class NhanVienMapper {
    public static NhanVien mapToModel(NhanVienDTO nhanVienDTO) {
        PhongBan phongBan = new PhongBan();
        phongBan.setMaPB(nhanVienDTO.getMaPhongBan());

        ChucVu chucVu = new ChucVu();
        chucVu.setMaChucVu(nhanVienDTO.getMaChucVu());



        NhanVien nhanVien = NhanVien.builder()
                .maNV(nhanVienDTO.getMaNV())
                .tenNV(nhanVienDTO.getTenNV())
                .ngaySinh(nhanVienDTO.getNgaySinh())
                .gioiTinh(nhanVienDTO.getGioiTinh())
                .diaChi(nhanVienDTO.getDiaChi())
                .soDT(nhanVienDTO.getSoDT())
                .ngayTuyenDung(nhanVienDTO.getNgayTuyenDung())
                .phongBan(phongBan)
                .chucVu(chucVu)

                .build();

        return nhanVien;
    }

    public static NhanVienDTO mapToDTO(NhanVien nhanVien) {


        NhanVienDTO nhanVienDTO = NhanVienDTO.builder()
                .maNV(nhanVien.getMaNV())
                .tenNV(nhanVien.getTenNV())
                .ngaySinh(nhanVien.getNgaySinh())
                .gioiTinh(nhanVien.getGioiTinh())
                .diaChi(nhanVien.getDiaChi())
                .soDT(nhanVien.getSoDT())
                .ngayTuyenDung(nhanVien.getNgayTuyenDung())
                .maPhongBan(nhanVien.getPhongBan().getMaPB())
                .maChucVu(nhanVien.getChucVu().getMaChucVu())

                .build();

        return nhanVienDTO;
    }
}
