package iuh.fit.se.ontapth02.controller;

import iuh.fit.se.ontapth02.model.Teacher;
import iuh.fit.se.ontapth02.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * @description: TeacherController
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getListTeacher(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teacher-list";
    }
}
