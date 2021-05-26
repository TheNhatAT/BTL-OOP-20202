package thenhat.code.managerwebapp.DAO.entity;

import thenhat.code.managerwebapp.model.entity.Schedule;

import java.util.List;

//-- this interface is used by Hibernate --
public interface ScheduleDAO {

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

    //== for algorithm
    List<Schedule> getListScheduleOfInstitute(String institute);

    List<String> getListInstitude();
}
