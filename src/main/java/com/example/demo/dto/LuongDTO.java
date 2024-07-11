package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LuongDTO {
    private Long maLuong;
    private Double soTien;
    private Long maNV;

    public Long getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(Long maLuong) {
        this.maLuong = maLuong;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }
}
