package com.example.demo.service.impl;

import com.example.demo.dto.PhongBanDTO;
import com.example.demo.mapper.PhongBanMapper;
import com.example.demo.models.NhanVien;
import com.example.demo.models.PhongBan;
import com.example.demo.reponsitory.PhongBanResponsitory;
import com.example.demo.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.mapper.PhongBanMapper.mapToDTO;
import static com.example.demo.mapper.PhongBanMapper.mapToModel;


@Service
public class PhongBanServiceImpl implements PhongBanService {
    @Autowired
    private PhongBanResponsitory phongBanResponsitory;

    @Override
    public List<PhongBanDTO> findPhongBan() {
        List<PhongBan> phongBans = phongBanResponsitory.findAll();
        return phongBans.stream().map(phongBan -> mapToDTO(phongBan)).collect(Collectors.toList());
    }



    @Override
    public PhongBan addPhongBan(PhongBanDTO phongBanDTO) {
        PhongBan phongBan = PhongBanMapper.mapToModel(phongBanDTO);
        return phongBanResponsitory.save(phongBan);
    }

    @Override
    public PhongBanDTO findID(Long maPB) {
        PhongBan phongBan = phongBanResponsitory.findById(maPB).get();
        return mapToDTO(phongBan);
    }

    @Override
    public void updatePB(PhongBanDTO phongBanDTO) {
        PhongBan phongBan = mapToModel(phongBanDTO);
         phongBanResponsitory.save(phongBan);
    }

    @Override
    public void deletePhongBan(Long maPB) {
        phongBanResponsitory.deleteById(maPB);
    }

    @Override
    public List<PhongBanDTO> searchPhongBan(String query) {
        List<PhongBan> phongBans = phongBanResponsitory.searchPhongBan(query);
        return phongBans.stream().map(phongBan -> mapToDTO(phongBan)).collect(Collectors.toList());
    }


}
