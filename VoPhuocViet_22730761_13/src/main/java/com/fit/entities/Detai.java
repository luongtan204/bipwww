package com.fit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "detai")
public class Detai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADETAI", nullable = false)
    private Integer madetai;

    @Column(name = "TENDETAI", length = 200)
    @NotBlank(message = "Tên đề tài không được để trống")
    @Size(min = 10, max = 100, message = "Tên đề tài phải có độ dài từ 10 đến 100 ký tự")
    private String tendetai;

    @Column(name = "NAM")
    @NotNull(message = "Năm không được để trống")

    private Integer nam;

    @Column(name = "MOTA", columnDefinition = "TEXT")
    @NotBlank(message = "Mô tả đề tài không được để trống")
    @Size(min = 10, max = 200, message = "Mô tả đề tài phải có độ dài từ 10 đến 200 ký tự")
    private String mota;

    @Column(name = "MADANGKY", length = 20)
    @NotBlank(message = "Mã đăng ký không được để trống")
    @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "Mã đăng ký phải có định dạng XX-YYY (XX: 2 ký tự in hoa, YYY: 3 chữ số)")
    private String madangky;

    @ManyToOne
    @JoinColumn(name = "MAGV", nullable = false)
    private GiangVien giangVien;

    public Detai() {
    }

    public Detai(String tendetai, Integer nam, String mota, GiangVien giangVien) {
        this.tendetai = tendetai;
        this.nam = nam;
        this.mota = mota;
        this.giangVien = giangVien;
    }

    // Getters and Setters
    public Integer getMadetai() {
        return madetai;
    }

    public void setMadetai(Integer madetai) {
        this.madetai = madetai;
    }

    public String getTendetai() {
        return tendetai;
    }

    public void setTendetai(String tendetai) {
        this.tendetai = tendetai;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMadangky() {
        return madangky;
    }

    public void setMadangky(String madangky) {
        this.madangky = madangky;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }
}