package com.example.demo.dto;

import com.example.demo.models.ChamCong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChamCongDTO  {
    private Long maChamCong;
    private LocalDate ngayChamCong;
    private String trangThai;
    private Long maNV;

    public Long getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(Long maChamCong) {
        this.maChamCong = maChamCong;
    }

    public LocalDate getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(LocalDate ngayChamCong) {
        this.ngayChamCong = ngayChamCong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }
}