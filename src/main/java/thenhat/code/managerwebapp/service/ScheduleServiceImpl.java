package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.ScheduleDAO;
import thenhat.code.managerwebapp.DAO.TeacherDAO;
import thenhat.code.managerwebapp.helper.Algorithm;
import thenhat.code.managerwebapp.model.Assigment;
import thenhat.code.managerwebapp.model.Schedule;
import thenhat.code.managerwebapp.model.Teacher;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    //== fields ==
    private ScheduleDAO scheduleDAO;
    private Algorithm algorithm;
    //== constructor injection ==

    @Autowired
    public ScheduleServiceImpl(Algorithm algorithm, ScheduleDAO scheduleDAO) {
        this.algorithm = algorithm;
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

    @Override
    public List<String> getAllInstitute() {
        return this.scheduleDAO.getListInstitude();
    }

    @Override
    public List<Assigment> AutoAssignment() {
        return this.algorithm.AutoAssignment();
    }

    @Override
    public void updateTeacher(List<Assigment> assigments) {
        for (int i = 0; i < assigments.size(); ++i) {
            Assigment temp = assigments.get(i);
            Schedule sche = this.scheduleDAO.getScheduleById(temp.getSchedules_id());
            if (temp.getCanbo2() == null){
                this.scheduleDAO.addSchedule(sche, temp.getCanbo1());
            } else {
                this.scheduleDAO.addSchedule(sche, temp.getCanbo1(), temp.getCanbo2());
            }
        }
    }
}
