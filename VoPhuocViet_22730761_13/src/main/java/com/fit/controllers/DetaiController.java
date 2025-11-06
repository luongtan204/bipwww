package com.fit.controllers;

import com.fit.entities.Detai;
import com.fit.services.DetaiService;
import com.fit.services.GiangVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detai")
public class DetaiController {

    @Autowired
    private DetaiService detaiService;

    @Autowired
    private GiangVienService giangVienService;

    // c. Thêm mới đề tài cho giảng viên (chỉ chairman có quyền)
    @PreAuthorize("hasRole('CHAIRMAN')")
    @GetMapping("/add/{magv}")
    public String showAddForm(@PathVariable String magv, Model model) {
        model.addAttribute("detai", new Detai());
        model.addAttribute("giangvien", giangVienService.getGiangVienById(magv));
        return "detai/form";
    }

    @PreAuthorize("hasRole('CHAIRMAN')")
    @PostMapping("/save")
    public String saveDetai(@Valid @ModelAttribute Detai detai,
                           BindingResult result,
                           @RequestParam String magv,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("giangvien", giangVienService.getGiangVienById(magv));
            return "detai/form";
        }

        detai.setGiangVien(giangVienService.getGiangVienById(magv));
        detaiService.saveDetai(detai);
        return "redirect:/giangvien/" + magv;
    }
}
