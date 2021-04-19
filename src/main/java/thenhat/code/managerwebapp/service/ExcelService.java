package thenhat.code.managerwebapp.service;

import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.model.Schedule;

import java.util.List;

public interface ExcelService {
    void save(MultipartFile file);
    List<Schedule> getAllSchedule();
}
