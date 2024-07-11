package com.example.demo.reponsitory;

import com.example.demo.models.ChamCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChamCongReponsitory extends JpaRepository<ChamCong, Long> {
    ChamCong findByNhanVien_MaNVAndNgayChamCong(Long maNV, LocalDate ngayChamCong);
    @Modifying
    @Query("SELECT c FROM ChamCong c WHERE c.ngayChamCong = :ngayChamCong")
    List<ChamCong> findByNgayChamCong(@Param("ngayChamCong") LocalDate ngayChamCong);
}
