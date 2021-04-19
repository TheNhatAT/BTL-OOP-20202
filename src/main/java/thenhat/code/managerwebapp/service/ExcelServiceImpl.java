package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.repository.ClassRepository;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.Schedule;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    //== fields ==
    ClassRepository classRepository;

    //== constructor injection ==
    @Autowired
    public ExcelServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    //== methods ==
    @Override
    public void save(MultipartFile file) {
        try {
            log.info("before saving by ExcelService");
            List<Schedule> scheduleList = ExcelHelper.excelToLichThi(file.getInputStream());

//            log.info("lichThiList = {}", lichThiList);
            classRepository.saveAll(scheduleList);
            log.info("after saving by ExcelService");
        } catch (IOException e) {
            log.info("Error is {}", e.toString());
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return classRepository.findAll();
    }
}
