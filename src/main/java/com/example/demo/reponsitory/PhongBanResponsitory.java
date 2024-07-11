package com.example.demo.reponsitory;

import com.example.demo.dto.PhongBanDTO;
import com.example.demo.models.NhanVien;
import com.example.demo.models.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhongBanResponsitory extends JpaRepository<PhongBan,Long> {
    Optional<PhongBan> findById(Long maPB);

    @Query("""
    SELECT pb FROM PhongBan pb WHERE

 
    pb.tenPB LIKE CONCAT('%', :query, '%')
    OR pb.moTa LIKE CONCAT('%', :query, '%')
   
    """)
    List<PhongBan> searchPhongBan(String query);
}
