package thenhat.code.managerwebapp.service.entity;

import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.model.entity.Schedule;

import java.util.List;

public interface ExcelService {
    void save(MultipartFile file);
    List<Schedule> getAllSchedule();
}
