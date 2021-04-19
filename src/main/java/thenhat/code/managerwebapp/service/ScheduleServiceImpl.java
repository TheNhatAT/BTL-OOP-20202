package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.ScheduleDAO;
import thenhat.code.managerwebapp.model.Schedule;

import java.util.List;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    //== fields ==
    private ScheduleDAO scheduleDAO;

    //== constructor injection ==
    @Autowired
    public ScheduleServiceImpl(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    //== methods ==
    @Override
    public void addSchedule(Schedule schedule) {
        this.scheduleDAO.addSchedule(schedule);
    }

    @Override
    public void addSchedule(Schedule schedule, Long id) {
        this.scheduleDAO.addSchedule(schedule, id);
    }

    @Override
    public void addSchedule(Schedule schedule, Long id_1, Long id_2) {
        this.scheduleDAO.addSchedule(schedule, id_1, id_2);
    }

    @Override
    public void addAllSchedules(List<Schedule> scheduleList) {
        this.scheduleDAO.addAllSchedules(scheduleList);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        this.scheduleDAO.updateSchedule(schedule);
    }

    @Override
    public List<Schedule> getListSchedule() {
        return this.scheduleDAO.getListSchedule();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return this.scheduleDAO.getScheduleById(id);
    }

    @Override
    public void removeScheduleById(Long id) {
        this.scheduleDAO.removeScheduleById(id);
    }

    @Override
    public List<Schedule> getListScheduleOfCodeClass(Long maLop) {
       return this.scheduleDAO.getListScheduleOfCodeClass(maLop);
    }

    @Override
    public List<Schedule> getListScheduleOfInstitute(String institute) {
        return this.scheduleDAO.getListScheduleOfInstitute(institute);
    }

    @Override
    public List<Schedule> getListScheduleOfTeacherId(Long id) {
        return this.scheduleDAO.getListScheduleOfTeacherId(id);
    }


}
