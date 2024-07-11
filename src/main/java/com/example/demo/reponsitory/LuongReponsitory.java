package com.example.demo.reponsitory;

import com.example.demo.models.Luong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LuongReponsitory extends JpaRepository< Luong , Long> {

    @Query("SELECT l FROM Luong l WHERE l.nhanVien.maNV = :maNV")
    Optional<Luong> findByNhanVienId(@Param("maNV") Long maNV);


}
