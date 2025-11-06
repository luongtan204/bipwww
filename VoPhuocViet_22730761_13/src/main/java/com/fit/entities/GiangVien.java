package com.fit.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "giangvien")
public class GiangVien {

    @Id
    @Column(name = "MAGV", nullable = false, length = 10)
    private String magv;

    @Column(name = "TENGV", length = 100)
    private String tengv;

    @Column(name = "LINHVUCNGHIECUU", length = 100)
    private String linhvucnghiencuu;

    @Column(name = "DIENTHOAI", length = 15)
    private String dienthoai;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @OneToMany(mappedBy = "giangVien", cascade = CascadeType.ALL)
    private Set<Detai> detais;

    public GiangVien() {
    }

    public GiangVien(String magv, String tengv, String linhvucnghiencuu, String email) {
        this.magv = magv;
        this.tengv = tengv;
        this.linhvucnghiencuu = linhvucnghiencuu;
        this.email = email;
    }

    // Getters and Setters
    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public String getTengv() {
        return tengv;
    }

    public void setTengv(String tengv) {
        this.tengv = tengv;
    }

    public String getLinhvucnghiencuu() {
        return linhvucnghiencuu;
    }

    public void setLinhvucnghiencuu(String linhvucnghiencuu) {
        this.linhvucnghiencuu = linhvucnghiencuu;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Detai> getDetais() {
        return detais;
    }

    public void setDetais(Set<Detai> detais) {
        this.detais = detais;
    }
}