package com.example.demo.service;

import com.example.demo.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAllRole();



    RoleDTO addRole(RoleDTO roleDTO);

    RoleDTO findIdRole(Long id);

    void updateRole(RoleDTO roleDTO);

    void deleteRole(Long id);
}
