package com.example.demo.service.impl;

import com.example.demo.dto.RoleDTO;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.models.Role;
import com.example.demo.reponsitory.RoleReponsitory;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleReponsitory roleReponsitory;

    @Override
    public List<RoleDTO> findAllRole() {
        List<Role> roleList = roleReponsitory.findAll();
        return roleList.stream().map(role -> RoleMapper.mapToDTO(role)).collect(Collectors.toList());
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Role role = RoleMapper.mapToModel(roleDTO);
        Role rolesave = roleReponsitory.save(role);
        return RoleMapper.mapToDTO(rolesave);
    }

    @Override
    public RoleDTO findIdRole(Long id) {
       Role role = roleReponsitory.findById(id).get();


        return RoleMapper.mapToDTO(role);
    }

    @Override
    public void updateRole(RoleDTO roleDTO) {
        Role role = RoleMapper.mapToModel(roleDTO);
        roleReponsitory.save(role);

    }

    @Override
    public void deleteRole(Long id) {
        roleReponsitory.deleteById(id);
    }


}
