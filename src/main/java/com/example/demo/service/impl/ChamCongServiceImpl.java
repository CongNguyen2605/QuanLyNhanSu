package com.example.demo.service.impl;

import com.example.demo.dto.ChamCongDTO;
import com.example.demo.mapper.ChamCongMapper;
import com.example.demo.models.ChamCong;
import com.example.demo.reponsitory.ChamCongReponsitory;
import com.example.demo.reponsitory.NhanVienResponistory;
import com.example.demo.service.ChamCongService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.mapper.ChamCongMapper.mapToDTO;

@Service
public class ChamCongServiceImpl implements ChamCongService {
    @Autowired
    private ChamCongReponsitory chamCongReponsitory;
    @Autowired
    private NhanVienResponistory nhanVienRepository;


    @Override
    public List<ChamCongDTO> findAllChamCong() {
        List<ChamCong> chamCongs = chamCongReponsitory.findAll();
        return chamCongs.stream().map(chamCong -> mapToDTO(chamCong)).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void updateChamCongList(List<ChamCongDTO> chamCongDTOs, String ngay) {
        LocalDate date = LocalDate.parse(ngay); // Adjust parsing format if needed

        for (ChamCongDTO chamCongDTO : chamCongDTOs) {
            ChamCong existingChamCong = chamCongReponsitory.findByNhanVien_MaNVAndNgayChamCong(chamCongDTO.getMaNV(), date);

            if (existingChamCong != null) {
                // Update the existing record
                existingChamCong.setTrangThai(chamCongDTO.getTrangThai());
                existingChamCong.setNgayChamCong(date);
                chamCongReponsitory.save(existingChamCong);
            } else {
                // Create a new record
                ChamCong chamCong = ChamCongMapper.mapToModel(chamCongDTO);
                chamCong.setNgayChamCong(date); // Ensure the date is set correctly
                chamCongReponsitory.save(chamCong);
            }
        }
    }
    @Override
    @Transactional
    public ChamCongDTO findByNhanVienAndNgay(Long maNV, String ngay) {
        LocalDate date = LocalDate.parse(ngay); // Adjust parsing format if needed
        ChamCong chamCong = chamCongReponsitory.findByNhanVien_MaNVAndNgayChamCong(maNV, date);

        // Check if chamCong is null and handle accordingly
        if (chamCong == null) {
            return null; // Or throw an exception or handle as needed
        }

        return ChamCongMapper.mapToDTO(chamCong);
    }
    @Override
    public List<ChamCong> findChamCongByNgayChamCong(LocalDate ngayChamCong) {
        return chamCongReponsitory.findByNgayChamCong(ngayChamCong);
    }




}
