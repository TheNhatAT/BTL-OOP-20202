package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.LichThi;
import thenhat.code.managerwebapp.service.LichThiService;

import java.util.Vector;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/lichThi")
public class LichThiController {

    //== fields ==
    LichThiService lichThiService;

    //== constructor injection ==
    @Autowired
    public LichThiController(LichThiService lichThiService) {
        this.lichThiService = lichThiService;
    }

    //== REST methods ==
    @PostMapping("/uploadFile")
    public List<LichThi> uploadFile(@RequestParam("file") MultipartFile file) {
        if(ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");
                List<LichThi> lichThiList = ExcelHelper.excelToLichThi(file.getInputStream());
                lichThiService.addAllLichThi(lichThiList);
                return lichThiList;
            }
            catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    //== upload and update ==
    @PostMapping("/uploadLichThi")
    public LichThi uploadLichThi(@RequestBody LichThi lichThi) {

        log.info("lichThi from client = {}", lichThi.toString());
        //-- invoke when use addLichThi --
        if(lichThi.getId() == null) {
            log.info("add lich thi = {}", lichThi.toString());

            //-- cần fix chỗ add (set them field LopHoc vào LichThi) -- đã fix --
            lichThiService.addLichThi(lichThi);
        } else {
            log.info("update lich thi = {}", lichThi.toString());

            //-- cần fix chỗ update (thay remove = merge)  -- Đã fix --
            lichThiService.updateLichThi(lichThi);
        }
        return lichThi;
    }

    @PostMapping("/removeLichThi")
    public LichThi removeLichThi(@RequestParam("id") int id) {

        LichThi lichThi = lichThiService.getLichThiById((long) id);
        lichThiService.removeLichThiById((long) id);

        return lichThi;
    }

    @PostMapping("/addLichThiWithGiamThi")
    public LichThi addLichThiWithGiamThi(@RequestBody LichThi lichThi, @RequestParam("id") Long id) {
        lichThiService.addLichThi(lichThi, id);
        return lichThiService.getLichThiById(lichThi.getId());
    }

    @PostMapping("/addLichThiWith2GiamThi")
    public LichThi addLichThiWithGiamThi(@RequestBody LichThi lichThi, @RequestParam("id_1") Long id_1, @RequestParam("id_2") Long id_2) {
        lichThiService.addLichThi(lichThi, id_1, id_2);
        return lichThiService.getLichThiById(lichThi.getId());
    }

    @GetMapping("/lichThi")
    public LichThi getLichThi(@RequestParam("id") int id) {
        return lichThiService.getLichThiById(Long.valueOf(id));
    }

    @GetMapping("/listLichThi")
    public List<LichThi> getListLichThi() {
            List<LichThi> listLichThi = lichThiService.getListLichThi();

            if (listLichThi.isEmpty()) {
                log.info("List is empty");
                return null;
            }
        return listLichThi;
    }

    //== chua debug do data chua co ==
    @GetMapping("/listLichThiOfLopHoc")
    public List<LichThi> getListLichThiOfLopHoc(@RequestParam("maLop") Long maLop) {
        List<LichThi> list = lichThiService.getListLichThiOfLopHocMaLop(maLop);

        if(list.isEmpty()) {
            log.info("Lop Hoc chua co lich thi");
            return null;
        }
        return list;
    }

    //== chua debug do data chua co ==
    @GetMapping("/listLichThiOfGiangVien")
    public List<LichThi> getListLichThiOfGiangVien(@RequestParam("id") Long id) {
        List<LichThi> list = lichThiService.getListLichThiOfGiangVienId(id);

        if(list.isEmpty()) {
            log.info("Giang Vien chua co lich thi");
            return null;
        }
        return list;
    }
    @GetMapping("/checkPhanCong")
    public Vector<Integer> checkPhanCong() {
        Vector<Integer> success = new Vector<Integer>();
        success.add(0);
        success.add(0);
        if (lichThiService.checkGiangVien() != success) {
            return lichThiService.checkGiangVien();
        }
        if (lichThiService.checkSV() != success) {
            return lichThiService.checkSV();
        }
        return success;
    }
}
