package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.repository.LichThiRepository;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.LichThi;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    //== fields ==
    LichThiRepository lichThiRepository;

    //== constructor injection ==
    @Autowired
    public ExcelServiceImpl(LichThiRepository lichThiRepository) {
        this.lichThiRepository = lichThiRepository;
    }

    //== methods ==
    @Override
    public void save(MultipartFile file) {
        try {
            log.info("before saving by ExcelService");
            List<LichThi> lichThiList = ExcelHelper.excelToLichThi(file.getInputStream());

//            log.info("lichThiList = {}", lichThiList);
            lichThiRepository.saveAll(lichThiList);
            log.info("after saving by ExcelService");
        } catch (IOException e) {
            log.info("Error is {}", e.toString());
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public List<LichThi> getAllLichThi() {
        return lichThiRepository.findAll();
    }
}
