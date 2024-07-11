package com.example.demo.service.impl;

import com.example.demo.dto.NhanVienDTO;
import com.example.demo.mapper.NhanVienMapper;
import com.example.demo.models.DuAn;
import com.example.demo.models.NhanVien;
import com.example.demo.reponsitory.ChucVuResponsitory;
import com.example.demo.reponsitory.NhanVienResponistory;
import com.example.demo.reponsitory.PhongBanResponsitory;
import com.example.demo.service.NhanVienService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.mapper.NhanVienMapper.mapToDTO;
import static com.example.demo.mapper.NhanVienMapper.mapToModel;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienResponistory nhanVienResponistory;
    @Autowired
    private ChucVuResponsitory chucVuRepository;

    @Autowired
    private PhongBanResponsitory phongBanRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NhanVienDTO> findNhanVien() {
        List<NhanVien> nhanViens = nhanVienResponistory.findAll();
        return nhanViens.stream().map(nhanVien -> mapToDTO(nhanVien)).collect(Collectors.toList());
    }


    @Transactional
    public NhanVienDTO addNhanVien(NhanVienDTO nhanVienDTO) {
        NhanVien nhanVien = NhanVienMapper.mapToModel(nhanVienDTO);
        NhanVien savedNhanVien = nhanVienResponistory.save(nhanVien);
        return NhanVienMapper.mapToDTO(savedNhanVien);
    }




    @Override
    public NhanVienDTO findID(Long maNV) {
        Optional<NhanVien> nhanVienOpt = nhanVienResponistory.findById(maNV);
        if (nhanVienOpt.isPresent()) {
            NhanVien nhanVien = nhanVienOpt.get();
            return NhanVienMapper.mapToDTO(nhanVien);
        } else {
            // Handle the case when NhanVien is not found
            return null;
        }

    }

    @Override
    public void updateNhanVien(NhanVienDTO nhanVienDTO) {
    NhanVien nhanVien = mapToModel(nhanVienDTO);
        nhanVienResponistory.save(nhanVien);
    }

    @Override
    public void deleteNhanVien(Long maNV) {
        nhanVienResponistory.deleteById(maNV);
    }

    @Override
    public List<NhanVienDTO> searchNhanVien(String query) {
        List<NhanVien> nhanViens = nhanVienResponistory.searchNhanVien(query);
        return  nhanViens.stream().map(nhanVien -> mapToDTO(nhanVien)).collect(Collectors.toList());
    }


}
