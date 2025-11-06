package com.fit.services;

import com.fit.entities.Detai;
import com.fit.reposities.DetaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetaiService {

    @Autowired
    private DetaiRepository detaiRepository;

    public List<Detai> getAllDetai() {
        return detaiRepository.findAll();
    }

    public Detai getDetaiById(String madetai) {
        return detaiRepository.findById(madetai).orElse(null);
    }

    public Detai saveDetai(Detai detai) {
        return detaiRepository.save(detai);
    }

    public void deleteDetai(String madetai) {
        detaiRepository.deleteById(madetai);
    }
}

