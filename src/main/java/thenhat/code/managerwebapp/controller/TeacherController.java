package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thenhat.code.managerwebapp.model.entity.Teacher;
import thenhat.code.managerwebapp.service.entity.TeacherService;

import java.util.List;

@Slf4j
@RestController
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
    @GetMapping
    public List<Teacher> getAllTeachers() {
        //== cần xử lý thêm các trường hợp ngoại lệ ==
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherByid(@PathVariable("id") Long id) {
        //== cần xử lý thêm các trường hợp ngoại lệ ==
        return teacherService.getTeacherById(id);
    }

    //== bug, can't access this url ==
    @PostMapping("/update")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        //== cần xử lý thêm các trường hợp ngoại lệ ==
        if (teacher.getId() == null) {
            log.info("add giang vien = {}", teacher);
            teacherService.addTeacher(teacher);
        } else {
            log.info("update giang vien = {}", teacher);
            teacherService.updateTeacher(teacher);
        }
        return teacher;
    }

    @GetMapping("/remove/{id}")
    public Teacher removeTeacher(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        log.info("remove giang vien = {}", teacher);
        teacherService.removeTeacherById(id);
        return teacher;
    }

    //== need to code ==
    /**
     * API: Đăng ký trông thi
     * API: Import giang vien bằng file
     */
    //== done debug ==
}
