package thenhat.code.managerwebapp.service.entity;

import thenhat.code.managerwebapp.model.entity.Assigment;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.model.entity.Teacher;

import java.util.List;

public interface ScheduleService {
    void addSchedule(Schedule schedule);

    void addSchedule(Schedule schedule, Long id);

    void addSchedule(Schedule schedule, Long id_1, Long id_2);

    void addAllSchedules(List<Schedule> scheduleList);

    void updateSchedule(Schedule schedule);

    List<Schedule> getListSchedule();

    Schedule getScheduleById(Long id);

    void removeScheduleById(Long id);

     List<Schedule> getListScheduleOfCodeClass(Long maLop);

     List<Schedule> getListScheduleOfTeacherId(Long id);

    List<Schedule> getListScheduleOfInstitute(String institute);

    List<String> getAllInstitute();

    List<Assigment> AutoAssignment();

    void updateTeacher(List<Assigment> assigments);

    List<Schedule> getListSchedulePaging(Integer page);
}
