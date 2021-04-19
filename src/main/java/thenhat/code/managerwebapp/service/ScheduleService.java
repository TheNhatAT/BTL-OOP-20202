package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.Schedule;

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
}
