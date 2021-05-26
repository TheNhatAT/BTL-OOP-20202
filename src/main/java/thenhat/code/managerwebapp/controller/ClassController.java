package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.entity.Class;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.service.entity.ClassService;
import thenhat.code.managerwebapp.service.entity.TeacherService;

import java.io.IOException;
import java.util.List;

@Slf4j
//@RestController
@Controller
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/classes")
public class ClassController {
    //== fields ==
    ClassService classService;
    TeacherService teacherService;

    //== constructor injection ==
    @Autowired
    public ClassController(ClassService classService, TeacherService teacherService) {
        this.classService = classService;
        this.teacherService = teacherService;
    }

    //== Lỗi ở định dạng file input == sửa sau ==
    @ResponseBody
    @PostMapping("/file")
    public List<Class> uploadAllClasses(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");
                //==  file excel cần cột tham chiếu về giảng viên theo id==//
                List<Class> classList = ExcelHelper.exelToLopHoc(file.getInputStream());
                classService.addAllClass(classList);
                return classList;
            } catch (IOException e) {
                log.info("upload failed!!!");
                return null;
            }
        }
        return null;
    }

    //== ok ==
    //== thymeleaf ==
    @GetMapping("/add")
    public String getClassInputForm(@ModelAttribute("class") Class aClass){
        return "fe/form-add-class";
    }
    @PostMapping("/add")
    public String uploadClass(@ModelAttribute("class") Class aClass, Model model) {
        log.info("add lop hoc = {}", aClass);
        if(aClass.getGiangVienId() != null) {
            classService.addClass(aClass, aClass.getGiangVienId());
        }
        else {
            classService.addClass(aClass);
        }
        return "redirect:/api/classes/add";
    }
    @GetMapping
    public String getAllClass(Model model) {
        log.info("start() get all lop hoc");
        List<Class> list = classService.getAllClasses();
        model.addAttribute("listClasses", list);
        return "fe/class";
    }

    @GetMapping("update/{id}")
    public String getUpdateScheduleForm(@PathVariable("id") Long id, @ModelAttribute("class") Class aClass, Model model){
        log.info("get update form {}", classService.getClassById(id));
        model.addAttribute("class", classService.getClassById(id));
        return "fe/form-update-class";
    }

    @PostMapping("update/{id}")
    public String updateClass(@PathVariable("id") Long id, @ModelAttribute("class") Class aClass) {
        log.info("post update lop hoc = {}", aClass.toString());
        if (aClass.getGiangVienId() != null) {
            aClass.setTeacher(teacherService.getTeacherById(aClass.getGiangVienId()));
        }
        classService.updateClass(aClass);
        return "redirect:/api/classes";
    }

    //== for delete ==
    @GetMapping("/remove/{id}")
    public String removeClassById(@PathVariable("id") Long id) {
        log.info("delete class by id = {}", id);
        classService.removeClassById(id);
        return "redirect:/api/classes";
    }

    //== JSON ==
    @ResponseBody
    @GetMapping("/{code}")
    public Class getClass(@PathVariable("code") Long maLop) {
        log.info("start() get lop hoc with ma lop = {}", maLop);
        return classService.getClassByCodeClass(maLop);
    }


    @ResponseBody
    @GetMapping("/teacher/{id}")
    public List<Class> getListClassOfTeacher(@PathVariable("id") Long id) {
        log.info("start() get list lop hoc of giang vien");
        return classService.getListClassOfTeacherId(id);
    }

    @ResponseBody
    @PostMapping("/teacher/{id}")
    public Class addClassWithTeacher(@RequestBody Class aClass, @PathVariable("id") Long id) {
        classService.addClass(aClass, id);
        return classService.getClassByCodeClass(aClass.getMaLop());
    }

    //== need to code ==
    /**
     * API: quản lý tổ chức thi
     */

    //== done debug ==

}
