package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.LopHoc;
import thenhat.code.managerwebapp.service.LopHocService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/lopHoc")
public class LopHocController {
    //== fields ==
    LopHocService lopHocService;

    //== constructor injection ==
    @Autowired
    public LopHocController(LopHocService lopHocService) {
        this.lopHocService = lopHocService;
    }

    //== Lỗi ở định dạng file input == sửa sau ==
    @PostMapping("/uploadFile")
    public List<LopHoc> uploadAllLopHoc(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");

                //==  file excel cần cột tham chiếu về giảng viên theo id==//
                List<LopHoc> lopHocList = ExcelHelper.exelToLopHoc(file.getInputStream());
                lopHocService.addAllLopHoc(lopHocList);
                return lopHocList;
            } catch (IOException e) {
                log.info("upload failed!!!");
                return null;
            }

        }
        return null;
    }

    //== ok ==
    @PostMapping("/uploadLopHoc")
    public LopHoc uploadLopHoc(@RequestBody LopHoc lopHoc) {
        if (lopHoc.getMaLop() == null) {
            log.info("add lop hoc = {}", lopHoc);
            lopHocService.addLopHoc(lopHoc);
        } else {
            log.info("update lop hoc = {}", lopHoc);
            lopHocService.updateLopHoc(lopHoc);
        }
        return lopHoc;
    }

    //== ok ==
    @GetMapping("/getLopHoc")
    public LopHoc getLopHoc(@RequestParam("ma lop") Long maLop) {
        log.info("start() get lop hoc with ma lop = {}", maLop);
        return lopHocService.getLopHocByMaLop(maLop);
    }

    //== ok ==
    @GetMapping("/getAllLopHoc")
    public List<LopHoc> getAllLopHoc() {
        log.info("start() get all lop hoc");
        return lopHocService.getAllLopHoc();
    }

    //== chưa có data về GV ==
    @GetMapping("/getLopHocByGiangVienId")
    public List<LopHoc> getListLopOfGiangVien(@RequestParam("id") Long id) {
        log.info("start() get list lop hoc of giang vien");
        return lopHocService.getListLopHocOfGiangVienId(id);
    }

    //== chua debug ==
    @PostMapping("/addLopHocWithGiangVien")
    public LopHoc addLopHocWithGiangVien(@RequestBody LopHoc lopHoc, @RequestParam("id") Long id) {
        lopHocService.addLopHoc(lopHoc, id);
        return lopHocService.getLopHocByMaLop(lopHoc.getMaLop());
    }
}
