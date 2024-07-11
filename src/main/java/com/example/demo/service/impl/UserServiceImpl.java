package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;

import com.example.demo.mapper.UserMapper;
import com.example.demo.models.NhanVien;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.reponsitory.NhanVienResponistory;
import com.example.demo.reponsitory.RoleReponsitory;
import com.example.demo.reponsitory.UserReponsitory;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    private RoleReponsitory roleReponsitory;
    @Autowired
    private NhanVienResponistory nhanVienResponistory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> findAllUser() {
        List<User> users = userReponsitory.findAll();
        return users.stream().map(user -> UserMapper.mapToDTO(user)).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public UserDTO addUser(UserDTO userDTO) {


        if (userReponsitory.existsById(userDTO.getMaNV())) {
            throw new IllegalArgumentException("Nhân viên này đã có tài khoản rồi vui lòng thêm nhân viên khác");
        }
        // Check if username already exists
        if (userReponsitory.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại, vui lòng nhập username khác");
        }

        Role role = roleReponsitory.findById(userDTO.getRoleIds())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        NhanVien nhanVien = nhanVienResponistory.findById(userDTO.getMaNV())
                .orElseThrow(() -> new RuntimeException("Nhân viên not found"));

        User user = UserMapper.mapToModel(userDTO, role, nhanVien);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()) );

        User savedUser = userReponsitory.save(user);
        return UserMapper.mapToDTO(savedUser);
    }

    @Override
    public UserDTO findIdUser(Long id) {
       User user = userReponsitory.findById(id).get();
        return UserMapper.mapToDTO(user);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        // Fetch the existing user
        User existingUser = userReponsitory.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if new username is already taken by another user
        if (userReponsitory.existsByUsernameAndIdNot(userDTO.getUsername(), userDTO.getId())) {
            throw new IllegalArgumentException("Username đã tồn tại, vui lòng nhập username khác");
        }

        // Fetch the new role and nhanVien
        Role role = roleReponsitory.findById(userDTO.getRoleIds())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        NhanVien nhanVien = nhanVienResponistory.findById(userDTO.getMaNV())
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));

        // Update the existing user with new values
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()) );
        existingUser.setRole(role);
        existingUser.setNhanVien(nhanVien);

        User updatedUser = userReponsitory.save(existingUser);
        return UserMapper.mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userReponsitory.deleteById(id);
    }


}
