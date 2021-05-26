package thenhat.code.managerwebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.helper.ExcelHelper;
import thenhat.code.managerwebapp.model.entity.Assigment;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.service.entity.ScheduleService;
import thenhat.code.managerwebapp.service.entity.TeacherService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8080") //-- for configuring allowed origins --
@RequestMapping("/api/schedules")
public class ScheduleController {

    //== fields ==
    ScheduleService scheduleService;
    TeacherService teacherService;

    //== constructor injection ==
    @Autowired
    public ScheduleController(ScheduleService scheduleService, TeacherService teacherService) {
        this.scheduleService = scheduleService;
        this.teacherService = teacherService;
    }

    //== upload and update ==
    //== thymeleaf ==
    @GetMapping("/add")
    public String getScheduleInputForm(@ModelAttribute("schedule") Schedule schedule){
        return "fe/form-add-schedule";
    }
    @PostMapping("/add")
    public String uploadSchedule(@ModelAttribute("schedule") Schedule schedule) {
        log.info("add lich thi = {}", schedule);
        if(schedule.getGiamThi1ID() == null) {
            scheduleService.addSchedule(schedule);
        } else if (schedule.getGiamThi1ID() != null && schedule.getGiamThi2ID() == null){
            scheduleService.addSchedule(schedule, schedule.getGiamThi1ID());
        }
        else {
            scheduleService.addSchedule(schedule, schedule.getGiamThi1ID(), schedule.getGiamThi2ID());
        }
        return "redirect:/api/schedules/add";
    }

    @GetMapping("/list")
    public String getListSchedule(Model model) {
        List<Schedule> listSchedule = scheduleService.getListSchedule();
        if (listSchedule.isEmpty()) {
            log.info("List is empty");
            return null;
        }
        model.addAttribute("listSchedules", listSchedule);
        return "fe/schedule";
    }
    @GetMapping("/update/{id}")
    public String getUpdateScheduleForm(@PathVariable("id") Long id, @ModelAttribute("schedule") Schedule schedule, Model model){
        model.addAttribute("schedule", scheduleService.getScheduleById(id));
        return "fe/form-update-schedule";
    }

    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable("id") Long id, @ModelAttribute("schedule") Schedule schedule) {
        log.info("update lich thi = {}", schedule.toString());
        if(schedule.getGiamThi1ID() != null) {
            schedule.setGiamThi1(teacherService.getTeacherById(schedule.getGiamThi1ID()));
        }
        if(schedule.getGiamThi2ID() != null) {
            schedule.setGiamThi2(teacherService.getTeacherById(schedule.getGiamThi2ID()));
        }
        scheduleService.updateSchedule(schedule);
        return "redirect:/api/schedules/list";
    }

    @GetMapping("/remove/{id}")
    public String removeSchedule(@PathVariable("id") Long id) {
        log.info("remove schedule by id = {}", id);
        scheduleService.removeScheduleById(id);
        return "redirect:/api/schedules/list";
    }

    //== check Assigment vs Schedule ==
    @GetMapping("/autoassignment")
    public String Autoassignment() {
        log.info("START ASSIGN!!!");
        List<Assigment> list = scheduleService.AutoAssignment();
        scheduleService.updateTeacher(list);
        return "redirect:/api/schedules/list";
    }

    //== JSON ==
    //== REST methods ==
    @ResponseBody
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

    @ResponseBody
    @PostMapping("/addLichThiWithGiamThi/{id}")
    public Schedule addScheduleWithTeacher_1(@RequestBody Schedule schedule, @PathVariable("id") Long id) {
        scheduleService.addSchedule(schedule, id);
        return scheduleService.getScheduleById(schedule.getId());
    }

    @ResponseBody
    @PostMapping("/addLichThiWith2GiamThi/{id_1}/{id_2}")
    public Schedule addScheduleWithTeacher_2(@RequestBody Schedule schedule, @PathVariable("id_1") Long id_1, @PathVariable("id_2") Long id_2) {
        scheduleService.addSchedule(schedule, id_1, id_2);
        return scheduleService.getScheduleById(schedule.getId());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable("id") int id) {
        return scheduleService.getScheduleById(Long.valueOf(id));
    }

    @ResponseBody
    @GetMapping("/class/{code}")
    public List<Schedule> getListScheduleOfClass(@PathVariable("code") Long maLop) {
        List<Schedule> list = scheduleService.getListScheduleOfCodeClass(maLop);
        if (list.isEmpty()) {
            log.info("Lop Hoc chua co lich thi");
            return null;
        }
        return list;
    }

    @ResponseBody
    @GetMapping("/teacher/{id}")
    public List<Schedule> getListScheduleOfTeacher(@PathVariable("id") Long id) {
        List<Schedule> list = scheduleService.getListScheduleOfTeacherId(id);
        if (list.isEmpty()) {
            log.info("Giang Vien chua co lich thi");
            return null;
        }
        return list;
    }

    /**
     * API: Phân công lịch thi tự động
     * API: DUyệt lịch thi tự động
     */

}
