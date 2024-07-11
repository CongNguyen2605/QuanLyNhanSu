// DuAn.java
package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DuAn")
public class DuAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDA;
    private String tenDA;
    private String moTa;

    @ManyToMany
    @JoinTable(
            name = "NhanVien_DuAn",
            joinColumns = @JoinColumn(name = "maDA"),
            inverseJoinColumns = @JoinColumn(name = "maNV")
    )
    @EqualsAndHashCode.Exclude
    private Set<NhanVien> nhanVien;


    public Set<NhanVien> getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(Set<NhanVien> nhanVien) {
        this.nhanVien = nhanVien;
    }
    @Override
    public int hashCode() {
        return Objects.hash(maDA); // Use only the unique identifier
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DuAn duAn = (DuAn) obj;
        return Objects.equals(maDA, duAn.maDA);
    }
}