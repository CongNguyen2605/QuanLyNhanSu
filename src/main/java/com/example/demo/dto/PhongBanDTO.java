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
public class PhongBanDTO {
    private Long maPB;
    @NotBlank(message = "Không được để rỗng tên phòng ban")
    private String tenPB;
    @NotBlank(message = "Không được để rỗng mô tả")
    private String moTa;
}
