package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.Schedule;
import thenhat.code.managerwebapp.service.ScheduleService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/schedules")
public class ScheduleController {

    //== fields ==
    ScheduleService scheduleService;

    //== constructor injection ==
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //== REST methods ==
    @PostMapping("/file")
    public List<Schedule> uploadFile(@RequestParam("file") MultipartFile file) {
        if(ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");
                List<Schedule> scheduleList = ExcelHelper.excelToLichThi(file.getInputStream());
                scheduleService.addAllSchedules(scheduleList);
                return scheduleList;
            }
            catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    //== upload and update ==
    @PostMapping("/")
    public Schedule uploadSchedule(@RequestBody Schedule schedule) {

        log.info("lichThi from client = {}", schedule.toString());
        //-- invoke when use addLichThi --
        if(schedule.getId() == null) {
            log.info("add lich thi = {}", schedule.toString());

            //-- cần fix chỗ add (set them field LopHoc vào LichThi) -- đã fix --
            scheduleService.addSchedule(schedule);
        } else {
            log.info("update lich thi = {}", schedule.toString());

            //-- cần fix chỗ update (thay remove = merge)  -- Đã fix --
            scheduleService.updateSchedule(schedule);
        }
        return schedule;
    }

    @PostMapping("/remove/{id}")
    public Schedule removeSchedule(@PathVariable("id") int id) {

        Schedule schedule = scheduleService.getScheduleById((long) id);
        scheduleService.removeScheduleById((long) id);

        return schedule;
    }

    @PostMapping("/addLichThiWithGiamThi")
    public Schedule addScheduleWithTeacher_1(@RequestBody Schedule schedule, @RequestParam("id") Long id) {
        scheduleService.addSchedule(schedule, id);
        return scheduleService.getScheduleById(schedule.getId());
    }

    @PostMapping("/addLichThiWith2GiamThi")
    public Schedule addScheduleWithTeacher_2(@RequestBody Schedule schedule, @RequestParam("id_1") Long id_1, @RequestParam("id_2") Long id_2) {
        scheduleService.addSchedule(schedule, id_1, id_2);
        return scheduleService.getScheduleById(schedule.getId());
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable("id") int id) {
        return scheduleService.getScheduleById(Long.valueOf(id));
    }

    @GetMapping("/list")
    public List<Schedule> getListSchedule() {
            List<Schedule> listSchedule = scheduleService.getListSchedule();

            if (listSchedule.isEmpty()) {
                log.info("List is empty");
                return null;
            }
        return listSchedule;
    }

    //== chua debug do data chua co ==
    @GetMapping("/class/{code}")
    public List<Schedule> getListScheduleOfClass(@PathVariable("code") Long maLop) {
        List<Schedule> list = scheduleService.getListScheduleOfCodeClass(maLop);

        if(list.isEmpty()) {
            log.info("Lop Hoc chua co lich thi");
            return null;
        }
        return list;
    }

    //== chua debug do data chua co ==
    @GetMapping("/teacher/{id}")
    public List<Schedule> getListScheduleOfTeacher(@PathVariable("id") Long id) {
        List<Schedule> list = scheduleService.getListScheduleOfTeacherId(id);

        if(list.isEmpty()) {
            log.info("Giang Vien chua co lich thi");
            return null;
        }
        return list;
    }

    //== need to code ==
    /**
     * API: Phân công lịch thi tự động
     * API: DUyệt lịch thi tự động
     */
//    @GetMapping("/checkPhanCong")
//    public Vector<Integer> checkPhanCong() {
//        Vector<Integer> success = new Vector<Integer>();
//        success.add(0);
//        success.add(0);
//        if (lichThiService.checkGiangVien() != success) {
//            return lichThiService.checkGiangVien();
//        }
//        if (lichThiService.checkSV() != success) {
//            return lichThiService.checkSV();
//        }
//        return success;
//    }
}
