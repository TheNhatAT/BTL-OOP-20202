package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.entity.Class;
import thenhat.code.managerwebapp.service.entity.ClassService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/classes")
public class ClassController {
    //== fields ==
    ClassService classService;

    //== constructor injection ==
    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    //== Lỗi ở định dạng file input == sửa sau ==
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
    @PostMapping("/add")
    public Class uploadClass(@RequestBody Class aClass) {
        log.info("add lop hoc = {}", aClass);
        classService.addClass(aClass);
        return aClass;
    }

    @PutMapping("/update")
    public Class updateClass(@RequestBody Class aClass) {
        log.info("update lop hoc = {}", aClass);
        classService.updateClass(aClass);
        return aClass;
    }

    //== ok ==
    @GetMapping("/{code}")
    public List<Class> getClass(@PathVariable("code") Long maLop) {
        log.info("start() get lop hoc with ma lop = {}", maLop);
        return classService.getClassByCodeClass(maLop);
    }

    @GetMapping
    public List<Class> getAllClass() {
        log.info("start() get all lop hoc");
        return classService.getAllClasses();
    }

    @GetMapping("/teacher/{id}")
    public List<Class> getListClassOfTeacher(@PathVariable("id") Long id) {
        log.info("start() get list lop hoc of giang vien");
        return classService.getListClassOfTeacherId(id);
    }

    @PostMapping("/add/{id}")
    public List<Class> addClassWithClass(@RequestBody Class aClass, @PathVariable("id") Long id) {
        classService.addClass(aClass, id);
        return classService.getClassByCodeClass(aClass.getMaLop());
    }

    //== need to code ==
    /**
     * API: quản lý tổ chức thi
     */

    //== done debug ==

}
