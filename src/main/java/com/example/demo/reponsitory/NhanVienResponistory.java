package com.example.demo.reponsitory;

import com.example.demo.models.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NhanVienResponistory extends JpaRepository<NhanVien,Long> {
    Optional<NhanVien> findById(Long maNV);
    @Query("""
    SELECT nv FROM NhanVien nv WHERE

    nv.tenNV LIKE CONCAT('%', :query, '%')
    OR nv.diaChi LIKE CONCAT('%', :query, '%')
    OR nv.gioiTinh LIKE CONCAT('%', :query, '%')
    OR nv.soDT LIKE CONCAT('%', :query, '%')
    """)





    List<NhanVien> searchNhanVien( String query);
}
