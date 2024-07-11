package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienDTO {
    private Long maNV;

    @NotBlank(message = "Không được để rỗng tên nhân viên")
    private String tenNV;

    @NotBlank(message = "Không được để rỗng ngày sinh")
    private String ngaySinh;

    @NotBlank(message = "Không được để rỗng giới tính")
    private String gioiTinh;

    @NotBlank(message = "Không được để rỗng địa chỉ")
    private String diaChi;

    @NotNull(message = "Không được để rỗng số điện thoại")
    private String soDT;

    @NotBlank(message = "Không được để rỗng ngày tuyển dụng")
    private String ngayTuyenDung;

    private Long maChucVu;
    private Long maPhongBan;
    private Set<Long> maDuAn;
    private Double luong;
    private Long id;
    private Set<Long> maChamCong;
}
