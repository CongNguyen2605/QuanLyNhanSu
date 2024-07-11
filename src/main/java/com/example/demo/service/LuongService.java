package com.example.demo.service;

import com.example.demo.dto.LuongDTO;

import java.util.List;
import java.util.Optional;

public interface LuongService {
    List<LuongDTO> findAllLuong();

    LuongDTO findIdLuong(Long maLuong);

    LuongDTO addLuong(LuongDTO luongDTO);


    LuongDTO findLuongByMaNV(Long maNV);
}
