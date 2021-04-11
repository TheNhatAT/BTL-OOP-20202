package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thenhat.code.managerwebapp.model.entity.Teacher;
import thenhat.code.managerwebapp.service.entity.TeacherService;

import java.util.List;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/teachers")
public class TeacherController {

    //== fields ==
    TeacherService teacherService;

    //== constructor injection ==
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //== REST methods ==
    //== thymeleaf ==
    @GetMapping
    public String getAllTeachers(Model model) {
        List<Teacher> list = teacherService.getAllTeachers();
        model.addAttribute("listTeachers", list);
        return "fe/teacher";
    }
    @GetMapping("/add")
    public String getTeachrInputForm(@ModelAttribute("teacher") Teacher teacher) {
        return "/fe/form-add-teacher";
    }
    @PostMapping("/add")
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
        log.info("add giang vien = {}", teacher);
        teacherService.addTeacher(teacher);
        return "redirect:/api/teachers/add";
    }

    //== thymeleaf ==
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable("id") Long id, @ModelAttribute("teacher") Teacher teacher, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "fe/form-update-teacher";
    }
    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") Long id, @ModelAttribute("teacher") Teacher teacher, Model model) {
        log.info("update giang vien = {}", teacher);
        teacherService.updateTeacher(teacher);
        return "redirect:/api/teachers";
    }

    @GetMapping("/remove/{id}")
    public String removeTeacher(@PathVariable("id") Long id) {
        log.info("remove giang vien by id = {}", id);
        teacherService.removeTeacherById(id);
        return "redirect:/api/teachers";
    }
    //== JSON ==
    @ResponseBody
    @GetMapping("/{id}")
    public Teacher getTeacherByid(@PathVariable("id") Long id) {
        return teacherService.getTeacherById(id);
    }


    //== need to code ==
    /**
     * API: Đăng ký trông thi
     * API: Import giang vien bằng file
     */
    //== done debug ==
}
