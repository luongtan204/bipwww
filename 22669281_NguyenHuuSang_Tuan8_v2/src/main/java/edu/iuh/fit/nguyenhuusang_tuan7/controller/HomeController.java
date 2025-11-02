package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import org.springframework.stereotype.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: HomeController
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    public HomeController() {
        super();
    }

    @GetMapping
    public String HomePage(Model model) {
        LocalDate date = LocalDate.now();
        String mess = "Welcome Thymeleaf!";
        model.addAttribute("message", mess);
        model.addAttribute("date", date.toString());
        return "home";
    }
}