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
public class ChucVuDTO {
    private Long maChucVu;
    @NotBlank(message = "Không được để rỗng tên phòng ban")
    private String tenChucVu;
    @NotBlank(message = "Không được để rỗng tên phòng ban")
    private String moTa;
}
