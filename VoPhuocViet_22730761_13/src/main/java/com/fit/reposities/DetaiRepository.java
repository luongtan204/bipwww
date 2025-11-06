package com.fit.reposities;

import com.fit.entities.Detai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetaiRepository extends JpaRepository<Detai, String> {
}

