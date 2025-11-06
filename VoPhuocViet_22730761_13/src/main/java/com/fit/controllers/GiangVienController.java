package com.fit.controllers;

import com.fit.entities.GiangVien;
import com.fit.services.DetaiService;
import com.fit.services.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class GiangVienController {

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private DetaiService detaiService;

    // a. Hiển thị danh sách giảng viên (student và chairman đều có quyền)
    @PreAuthorize("hasAnyRole('STUDENT', 'CHAIRMAN')")
    @GetMapping
    public String listGiangVien(Model model) {
        model.addAttribute("giangviens", giangVienService.getAllGiangVien());
        return "giangvien/list";
    }

    // b. Chọn giảng viên thì hiển thị các đề tài thuộc về giảng viên (student và chairman đều có quyền)
    @PreAuthorize("hasAnyRole('STUDENT', 'CHAIRMAN')")
    @GetMapping("/giangvien/{magv}")
    public String viewGiangVienDetail(@PathVariable String magv, Model model) {
        GiangVien giangVien = giangVienService.getGiangVienById(magv);
        model.addAttribute("giangvien", giangVien);
        model.addAttribute("detais", giangVien.getDetais());
        return "giangvien/detail";
    }
}
