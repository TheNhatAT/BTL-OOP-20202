package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thenhat.code.managerwebapp.model.GiangVien;
import thenhat.code.managerwebapp.model.LichThi;
import thenhat.code.managerwebapp.service.GiangVienService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/giangVien")
public class GiangVienController {

    //== fields ==
    GiangVienService giangVienService;

    //== constructor injection ==
    @Autowired
    public GiangVienController(GiangVienService giangVienService) {
        this.giangVienService = giangVienService;
    }

    //== REST methods ==
    @GetMapping("/listGiangVien")
    public List<GiangVien> getAllGiangVien() {
        //== cần xử lý thêm các trường hợp ngoại lệ ==
        return giangVienService.getAllGiangVien();
    }

    @GetMapping("/giangVienById")
    public GiangVien getGiangVienByid(@RequestParam("id") Long id) {
        //== cần xử lý thêm các trường hợp ngoại lệ ==
        return giangVienService.getGiangVienById(id);
    }

    @PostMapping("/updateGiangVien")
    public GiangVien updateGiangVien(GiangVien giangVien) {
        //== cần xử lý thêm các trường hợp ngoại lệ ==
        if (giangVien.getId() == null) {
            log.info("upload giang vien = {}", giangVien);
            giangVienService.addGiangVien(giangVien);
        } else {
            log.info("giang vien before update = {}", giangVienService.getGiangVienById(giangVien.getId()));
            giangVienService.updateGiangVien(giangVien);
            log.info("giang vien after update = {}", giangVienService.getGiangVienById(giangVien.getId()));
        }
        return giangVienService.getGiangVienById(giangVien.getId());
    }

    @GetMapping("removeGiangVien")
    public GiangVien removeGiangVien(@RequestParam("id") Long id) {
        GiangVien giangVien = giangVienService.getGiangVienById(id);
        log.info("remove giang vien = {}", giangVien);
        giangVienService.removeGiangVienById(id);
        return giangVien;
    }

    //== chưa debug ==
}
