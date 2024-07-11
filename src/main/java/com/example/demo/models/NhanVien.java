package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNV;
    private String tenNV;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String soDT;
    private String ngayTuyenDung;

    @ManyToOne
    @JoinColumn(name = "maChucVu")
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "maPhongBan")
    private PhongBan phongBan;

    @ManyToMany(mappedBy = "nhanVien")
    @EqualsAndHashCode.Exclude
    private Set<DuAn> duAn;

    @OneToOne(mappedBy = "nhanVien" )
    private Luong luong;
    @OneToOne(mappedBy = "nhanVien" )
    private User user;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private Set<ChamCong> chamCong;

    public Luong getLuong() {
        return luong;
    }

    public void setLuong(Luong luong) {
        this.luong = luong;
    }
    @Override
    public int hashCode() {
        return Objects.hash(maNV); // Use only the unique identifier
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NhanVien nhanVien = (NhanVien) obj;
        return Objects.equals(maNV, nhanVien.maNV);
    }
}