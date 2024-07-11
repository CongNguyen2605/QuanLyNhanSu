package com.example.demo.service.impl;

import com.example.demo.dto.LuongDTO;
import com.example.demo.mapper.LuongMapper;
import com.example.demo.mapper.NhanVienMapper;
import com.example.demo.models.Luong;
import com.example.demo.reponsitory.LuongReponsitory;
import com.example.demo.service.LuongService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LuongServiceImpl implements LuongService {
    @Autowired
    private LuongReponsitory luongReponsitory;

    @Override
    public List<LuongDTO> findAllLuong() {
       List<Luong> luongs = luongReponsitory.findAll();
        return luongs.stream().map(luong -> LuongMapper.mapToDTO(luong)).collect(Collectors.toList());
    }

    @Override
    public LuongDTO findIdLuong(Long maLuong) {
        Luong luong = luongReponsitory.findById(maLuong).get();
        return LuongMapper.mapToDTO(luong);
    }

    @Override

    @Transactional
    public LuongDTO addLuong(LuongDTO luongDTO) {
        Long maNV = luongDTO.getMaNV();

        // Check if a record already exists for this employee
        Optional<Luong> existingLuongOptional = luongReponsitory.findByNhanVienId(maNV);

        if (existingLuongOptional.isPresent()) {
            // Update the existing record
            Luong existingLuong = existingLuongOptional.get();
            existingLuong.setSoTien(luongDTO.getSoTien());
            // Optionally update other fields as needed

            Luong updatedLuong = luongReponsitory.save(existingLuong);
            return LuongMapper.mapToDTO(updatedLuong);
        } else {
            // Create a new record
            Luong newLuong = LuongMapper.mapToModel(luongDTO);
            Luong savedLuong = luongReponsitory.save(newLuong);
            return LuongMapper.mapToDTO(savedLuong);
        }
    }


    @Override
    public LuongDTO findLuongByMaNV(Long maNV) {
        return luongReponsitory.findByNhanVienId(maNV)
                .map(LuongMapper::mapToDTO)
                .orElse(null);
    }


}
