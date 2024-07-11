package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.NhanVien;
import com.example.demo.models.Role;
import com.example.demo.models.User;

public class UserMapper {

    public static User mapToModel(UserDTO userDTO, Role role, NhanVien nhanVien) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .nhanVien(nhanVien)
                .role(role)
                .build();
    }

    public static UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .maNV(user.getNhanVien().getMaNV())
                .roleIds(user.getRole().getId())
                .build();
    }

    public static User mapToModel1(UserDTO userDTO) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(userDTO.getMaNV());
        Role role = new Role();
        role.setId(userDTO.getRoleIds());
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .nhanVien(nhanVien)
                .role(role)
                .build();
    }
}
