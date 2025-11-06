package com.fit.reposities;

import com.fit.entities.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, String> {
    GiangVien findByEmail(String email);
}

