package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Luong")
public class Luong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLuong;
    private Double soTien;

    @OneToOne
    @JoinColumn(name = "maNV")
    private NhanVien nhanVien;

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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}