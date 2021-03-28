package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.LichThi;
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
    public LopHocController(LopHocService lopHocService){
        this.lopHocService = lopHocService;
    }

    @PostMapping("uploadFile")
    public List<LopHoc> uploadAllLopHoc(@RequestParam("file") MultipartFile file) {
        if(ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");
                List<LopHoc> lopHocList = ExcelHelper.exelToLopHoc(file.getInputStream());
                lopHocService.addAllLopHoc(lopHocList);
                return lopHocList;
            }
            catch (IOException e) {
                log.info("upload failed!!!");
                return null;
            }

        }
        return null;
    }

    //== chưa debug Lop Hoc API ==
}
