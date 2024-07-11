package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Không được để rỗng tên đăng nhập")
    private String username;
    @NotBlank(message = "Không được để rỗng mật khẩu ")
    private String password;
    private Long roleIds; // Assuming this is used to store role ID
    private Long maNV; // Assuming this is used to store employee ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long roleIds) {
        this.roleIds = roleIds;
    }

    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }
}
