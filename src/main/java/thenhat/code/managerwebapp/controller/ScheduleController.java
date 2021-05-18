package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.service.entity.ScheduleService;

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
        if (ExcelHelper.hasExelFormat(file)) {
            try {
                log.info("start() upload");
                List<Schedule> scheduleList = ExcelHelper.excelToLichThi(file.getInputStream());
                scheduleService.addAllSchedules(scheduleList);
                return scheduleList;
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    //== upload and update ==
    @PostMapping("/add")
    public Schedule uploadSchedule(@RequestBody Schedule schedule) {
        log.info("add lich thi = {}", schedule);
        scheduleService.addSchedule(schedule);
        return schedule;
    }

    @PutMapping("/update")
    public Schedule updateSchedule(@RequestBody Schedule schedule) {
        log.info("update lich thi = {}", schedule.toString());
        scheduleService.updateSchedule(schedule);
        return schedule;
    }

    @PostMapping("/remove/{id}")
    public Schedule removeSchedule(@PathVariable("id") int id) {
        log.info("remove schedule with id = {}", id);
        Schedule schedule = scheduleService.getScheduleById((long) id);
        scheduleService.removeScheduleById((long) id);
        return schedule;
    }

    //== need test
    @PostMapping("/addLichThiWithGiamThi/{id}")
    public Schedule addScheduleWithTeacher_1(@RequestBody Schedule schedule, @PathVariable("id") Long id) {
        scheduleService.addSchedule(schedule, id);
        return scheduleService.getScheduleById(schedule.getId());
    }

    //== need test
    @PostMapping("/addLichThiWith2GiamThi/{id_1}/{id_2}")
    public Schedule addScheduleWithTeacher_2(@RequestBody Schedule schedule, @PathVariable("id_1") Long id_1, @PathVariable("id_2") Long id_2) {
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

    @GetMapping("/class/{code}")
    public List<Schedule> getListScheduleOfClass(@PathVariable("code") Long maLop) {
        List<Schedule> list = scheduleService.getListScheduleOfCodeClass(maLop);

        if (list.isEmpty()) {
            log.info("Lop Hoc chua co lich thi");
            return null;
        }
        return list;
    }

    @GetMapping("/teacher/{id}")
    public List<Schedule> getListScheduleOfTeacher(@PathVariable("id") Long id) {
        List<Schedule> list = scheduleService.getListScheduleOfTeacherId(id);

        if (list.isEmpty()) {
            log.info("Giang Vien chua co lich thi");
            return null;
        }
        return list;
    }

    //== need to merge ==
    /**
     * API: Phân công lịch thi tự động
     * API: DUyệt lịch thi tự động
     */

}
