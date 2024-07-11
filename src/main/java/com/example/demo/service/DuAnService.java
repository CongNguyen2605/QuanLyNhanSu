package com.example.demo.service;

import com.example.demo.dto.DuAnDTO;
import com.example.demo.models.DuAn;
import com.example.demo.models.NhanVien;

import java.util.List;
import java.util.Set;

public interface DuAnService {
    List<DuAnDTO> findDuAn();

    DuAnDTO addDuAn(DuAnDTO duAnDTO);


    DuAnDTO findIdDuAn(Long maDA);

    void updateDuAn(DuAnDTO duAnDTO);

    void deleteDuAn(Long maDA);


    Set<NhanVien> viewAllNhanVienInDuAn (Long maDA);
    void deleteNhanVienFromDuAn(Long maDA,Long maNV);

    void addNhanVienToDuAn(DuAnDTO duAnDTO);
}
