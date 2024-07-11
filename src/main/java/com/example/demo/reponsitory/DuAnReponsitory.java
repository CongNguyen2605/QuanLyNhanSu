package com.example.demo.reponsitory;

import com.example.demo.models.DuAn;
import com.example.demo.models.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DuAnReponsitory extends JpaRepository<DuAn,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE DuAn d SET d.nhanVien = :nhanViens WHERE d.maDA = :maDA")
    int updateNhanViensInDuAn(Long maDA, Set<NhanVien> nhanViens);
    DuAn findByMaDA(Long maDA);


}
