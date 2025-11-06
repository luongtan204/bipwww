package com.fit.services;

import com.fit.entities.GiangVien;
import com.fit.reposities.GiangVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepository;

    public List<GiangVien> getAllGiangVien() {
        return giangVienRepository.findAll();
    }

    public GiangVien getGiangVienById(String magv) {
        return giangVienRepository.findById(magv).orElse(null);
    }

    public GiangVien saveGiangVien(GiangVien giangVien) {
        return giangVienRepository.save(giangVien);
    }

    public void deleteGiangVien(String magv) {
        giangVienRepository.deleteById(magv);
    }
}

