package com.example.demo.reponsitory;

import com.example.demo.models.ChucVu;
import com.example.demo.models.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChucVuResponsitory extends JpaRepository<ChucVu , Long> {
    Optional<ChucVu> findById(Long maChucVu);
}
