package iuh.fit.se.ontapth02.controller;

import iuh.fit.se.ontapth02.model.Clazz;
import iuh.fit.se.ontapth02.service.clazz.ClazzService;
import iuh.fit.se.ontapth02.service.teacher.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @description: ClazzController
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Controller
@RequestMapping("/clazzes")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private TeacherService teacherService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping
    public String getListClazz(Model model) {
        List<Clazz> clazzes = clazzService.findAll();
        model.addAttribute("clazzes", clazzes);
        return "clazz-list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public String getClazzById(@PathVariable("id") Integer id, Model model) {
        Clazz clazz = clazzService.findById(id);
        model.addAttribute("clazz", clazz);
        return "clazz-detail";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/filter-by-month")
    public String getListClazzByMonthStartDateBetween(int fromMonth, int toMonth, Model model) {
        List<Clazz> clazzes = clazzService.findByMonthStartDateBetween(fromMonth, toMonth);
        model.addAttribute("clazzes", clazzes);
        return "clazz-list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/filter-by-name")
    public String getListClazzByClassNameOrTeacherName(String keyword, Model model) {
        List<Clazz> clazzes = clazzService.findByClassNameOrTeacherName(keyword);
        model.addAttribute("clazzes", clazzes);
        return "clazz-list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("clazz", new Clazz());
        model.addAttribute("teachers", teacherService.findAll());
        return "clazz-add-form";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("clazz") Clazz clazz,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherService.findAll());
            return "clazz-add-form";
        }
        clazzService.save(clazz);
        return "redirect:/clazzes";
    }
}
