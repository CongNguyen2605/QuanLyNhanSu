package com.example.demo.service;

import com.example.demo.dto.DuAnDTO;
import com.example.demo.dto.NhanVienDTO;

import java.util.List;

public interface NhanVienService {
   List<NhanVienDTO> findNhanVien();

    NhanVienDTO addNhanVien(NhanVienDTO nhanVienDTO);

    NhanVienDTO findID(Long maNV);

    void updateNhanVien(NhanVienDTO nhanVienDTO);

    void deleteNhanVien(Long maNV);

    List<NhanVienDTO> searchNhanVien(String query);


}
