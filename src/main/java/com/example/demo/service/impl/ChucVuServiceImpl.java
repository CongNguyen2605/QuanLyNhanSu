package com.example.demo.service.impl;

import com.example.demo.dto.ChucVuDTO;
import com.example.demo.mapper.ChucVuMapper;
import com.example.demo.models.ChucVu;
import com.example.demo.reponsitory.ChucVuResponsitory;
import com.example.demo.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.demo.mapper.ChucVuMapper.mapToDTO;
import static com.example.demo.mapper.ChucVuMapper.mapToModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    private ChucVuResponsitory chucVuResponsitory;
    @Override
    public List<ChucVuDTO> findChucVu() {
        List<ChucVu> chucVus = chucVuResponsitory.findAll();
        return chucVus.stream().map(chucVu -> mapToDTO(chucVu)).collect(Collectors.toList());
    }

    @Override
    public ChucVu addChucVu(ChucVuDTO chucVuDTO) {
       ChucVu chucVu = ChucVuMapper.mapToModel(chucVuDTO);

        return chucVuResponsitory.save(chucVu);
    }

    @Override
    public ChucVuDTO findID(Long maChucVu) {
        ChucVu chucVu = chucVuResponsitory.findById(maChucVu).get();
        return mapToDTO(chucVu);
    }

    @Override
    public void updateChucVu(ChucVuDTO chucVuDTO) {
        ChucVu chucVu = mapToModel(chucVuDTO);
        chucVuResponsitory.save(chucVu);
    }

    @Override
    public void deleteChucVu(Long maChucVu) {
        chucVuResponsitory.deleteById(maChucVu);
    }


}
