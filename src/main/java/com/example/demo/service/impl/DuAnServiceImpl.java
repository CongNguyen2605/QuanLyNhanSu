package com.example.demo.service.impl;

import com.example.demo.dto.DuAnDTO;
import com.example.demo.mapper.DuAnMapper;
import com.example.demo.models.DuAn;
import com.example.demo.models.NhanVien;
import com.example.demo.reponsitory.DuAnReponsitory;
import com.example.demo.reponsitory.NhanVienResponistory;
import com.example.demo.service.DuAnService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.mapper.DuAnMapper.mapToDTO;
import static com.example.demo.mapper.DuAnMapper.mapToModel;

@Service
@Transactional
public class DuAnServiceImpl implements DuAnService {
    @Autowired
    private DuAnReponsitory duAnReponsitory;
    @Autowired
    private NhanVienResponistory nhanVienResponistory;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<DuAnDTO> findDuAn() {
        List<DuAn> duAn = duAnReponsitory.findAll();
        return duAn.stream().map(duAn1 -> mapToDTO(duAn1)).collect(Collectors.toList());
    }
    @Transactional
    public DuAnDTO addDuAn(DuAnDTO duAnDTO) {
        DuAn duAn = DuAnMapper.mapToModel(duAnDTO);
        DuAn savedDuAn = duAnReponsitory.save(duAn);
        return DuAnMapper.mapToDTO(savedDuAn);
    }



    @Override
    public DuAnDTO findIdDuAn(Long maDA) {
        DuAn duAn = duAnReponsitory.findById(maDA).get();
        return mapToDTO(duAn);
    }

    @Override
    public void updateDuAn(DuAnDTO duAnDTO) {
        DuAn duAn = mapToModel(duAnDTO);
        duAnReponsitory.save(duAn);
    }

    @Override
    public void deleteDuAn(Long maDA) {
        duAnReponsitory.deleteById(maDA);
    }



    @Override
    @Transactional
    public void addNhanVienToDuAn(DuAnDTO duAnDTO) {
        DuAn duAn = duAnReponsitory.findById(duAnDTO.getMaDA()).get();

        Set<NhanVien>  nhanVienSet = duAnDTO.getMaNV().stream()
                .map(maNV -> {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNV(maNV);
                    return nhanVien;
                })
                .collect(Collectors.toSet());
        duAn.setNhanVien(nhanVienSet);

        duAnReponsitory.save(duAn);
    }
    public Set<NhanVien> viewAllNhanVienInDuAn(Long maDA) {
        DuAn duAn = duAnReponsitory.findById(maDA).get();

        return duAn.getNhanVien();
    }

    @Override
    public void deleteNhanVienFromDuAn(Long maDA, Long maNV) {
        DuAn duAn = duAnReponsitory.findById(maDA).get();
        NhanVien nhanVien = nhanVienResponistory.findById(maNV).get();
        duAn.getNhanVien().remove(nhanVien);
        duAnReponsitory.save(duAn);
    }


}
