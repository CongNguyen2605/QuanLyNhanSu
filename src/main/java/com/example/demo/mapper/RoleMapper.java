package com.example.demo.mapper;

import com.example.demo.dto.RoleDTO;
import com.example.demo.models.Role;

public class RoleMapper {
    public static Role mapToModel(RoleDTO roleDTO){
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .build();
    }
    public static RoleDTO mapToDTO(Role role){
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
