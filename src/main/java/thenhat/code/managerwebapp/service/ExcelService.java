package thenhat.code.managerwebapp.service;

import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.model.LichThi;

import java.util.List;

public interface ExcelService {
    void save(MultipartFile file);
    List<LichThi> getAllLichThi();
}
