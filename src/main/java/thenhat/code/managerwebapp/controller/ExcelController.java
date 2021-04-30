package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.message.ResponseMessage;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.service.entity.ExcelService;

import java.util.List;

//-- this class just for testing by using Sring Data JPA --
@Slf4j
@Controller
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/excel")
public class ExcelController {

    //== fields ==
    ExcelService excelService;

    //== constructor injection ==
    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    //== REST Methods ==
    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");
                excelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!!!";
                log.info(message);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/listLichThi")
    public ResponseEntity<List<Schedule>> getListLichThi() {
        try {
            List<Schedule> scheduleList = excelService.getAllSchedule();

            if (scheduleList.isEmpty()) {
                log.info("List is empty!!!");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(scheduleList, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error when getListLichThi");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

