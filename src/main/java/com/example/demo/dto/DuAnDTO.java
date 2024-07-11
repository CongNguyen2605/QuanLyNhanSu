package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuAnDTO {
    private Long maDA;
    @NotBlank(message = "Không được để rỗng tên dự án")
    private String tenDA;
    @NotBlank(message = "Không được để rỗng mô tả")
    private String moTa;
    private Set<Long> maNV = new HashSet<>();


}

