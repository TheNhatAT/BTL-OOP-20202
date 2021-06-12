package thenhat.code.managerwebapp.service.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.entity.ScheduleDAO;
import thenhat.code.managerwebapp.DAO.entity.TeacherDAO;
import thenhat.code.managerwebapp.DAO.entity.TeacherDAOImpl;
import thenhat.code.managerwebapp.helper.Algorithm;
import thenhat.code.managerwebapp.model.entity.Assigment;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.model.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    //== fields ==
    private ScheduleDAO scheduleDAO;
    private Algorithm algorithm;
    private TeacherDAO teacherDAO;
    //== constructor injection ==
    @Autowired
    public ScheduleServiceImpl(ScheduleDAO scheduleDAO, Algorithm algorithm, TeacherDAO teacherDAO) {
        this.scheduleDAO = scheduleDAO;
        this.algorithm = algorithm;
        this.teacherDAO = teacherDAO;
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
    public List<Schedule> getListSchedulePaging(Integer page) {
        return this.scheduleDAO.getListSchedulePaging(page);
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
    public List<Schedule> getListScheduleOfTeacherId(Long id) {
        return this.scheduleDAO.getListScheduleOfTeacherId(id);
    }

    @Override
    public List<Schedule> getListScheduleOfInstitute(String institute) {
        return this.scheduleDAO.getListScheduleOfInstitute(institute);
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
        List<Schedule> list = new ArrayList<>();
        for (int i = 0; i < assigments.size(); ++i) {
            Assigment temp = assigments.get(i);
            Schedule sche = this.scheduleDAO.getScheduleById(temp.getSchedules_id());
            if (temp.getCanbo2() == null) {
                Teacher teacher = this.teacherDAO.getTeacherById(temp.getCanbo1());
                teacher.setTrongThi(true);
                this.teacherDAO.updateTeacher(teacher);
                this.scheduleDAO.addSchedule(sche, temp.getCanbo1());
            } else {
                Teacher teacher1 = this.teacherDAO.getTeacherById(temp.getCanbo1());
                Teacher teacher2 = this.teacherDAO.getTeacherById(temp.getCanbo2());
                teacher1.setTrongThi(true);
                teacher1.setTrongThi(true);
                this.teacherDAO.updateTeacher(teacher1);
                this.teacherDAO.updateTeacher(teacher2);
                this.scheduleDAO.addSchedule(sche, temp.getCanbo1(), temp.getCanbo2());
            }
        }
    }


}
