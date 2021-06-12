package thenhat.code.managerwebapp.DAO.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.entity.Class;
import thenhat.code.managerwebapp.model.entity.Schedule;
import thenhat.code.managerwebapp.model.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@Transactional
//-- use Hibernate|JPA --
public class ScheduleDAOImpl implements ScheduleDAO {

    //== fields ==
    @PersistenceContext
    private EntityManager em;

    //== REST methods ==
    @Override
    public void addSchedule(Schedule schedule) {
        log.info("start() add lich thi in DAO = {}", schedule.toString());
        em.persist(schedule);
        log.info("finish add lich thi");
    }

    @Override
    public void addSchedule(Schedule schedule, Long id) {
        log.info("start() add lich thi in DAO = {} with 1 giam thi has id = {}", schedule.toString(), id);
        schedule.setGiamThi1(em.find(Teacher.class, id));
        em.merge(schedule);
//        em.persist(schedule);
        log.info("finish add lich thi with 1 giam thi");
    }

    @Override
    public void addSchedule(Schedule schedule, Long id_1, Long id_2) {
        log.info("start() add lich thi in DAO = {} with giam thi 1 has id_1 = {}, id_2 = {}", schedule.toString(), id_1, id_2);
        schedule.setGiamThi1(em.find(Teacher.class, id_1));
        schedule.setGiamThi2(em.find(Teacher.class, id_2));
        em.merge(schedule);
        log.info("finish add lich thi with giam thi");
    }

    @Override
    public void addAllSchedules(List<Schedule> scheduleList) {
        for (Schedule lichthi :
                scheduleList) {
            em.persist(lichthi);
        }
        log.info("List Lich Thi saved");
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        em.merge(schedule);
        log.info("updated lich thi = {}", schedule);
    }

    @Override
    public List<Schedule> getListSchedule() {
        List<Schedule> scheduleList = em.createNativeQuery("SELECT * FROM schedule WHERE (is_deleted != true or is_deleted is null) order by lich_thi_id", Schedule.class).getResultList();
        log.info("Select all Lich Thi elements in DB");
        return scheduleList;
    }

    @Override
    public Schedule getScheduleById(Long id) {
        Schedule schedule = em.find(Schedule.class, id);
        em.close();
        return schedule;
    }

    @Override
    public void removeScheduleById(Long id) {
        Schedule schedule = em.find(Schedule.class, id);
        schedule.setIsDeleted(true);
        em.merge(schedule);
    }

    @Override
    public List<Schedule> getListScheduleOfCodeClass(Long maLop) {
        log.info("start() get list lich thi of lop hoc has ma lop = {}", maLop);
        List<Schedule> list = em.createNativeQuery("SELECT * FROM schedule WHERE (is_deleted != true or is_deleted is null) and ma_lop = " + maLop.toString(), Schedule.class).getResultList();
        log.info("finish() get list lich thi of lop hoc has ma lop = {}", maLop);
        return list;
    }

    @Override
    public List<Schedule> getListScheduleOfTeacherId(Long id) {
        log.info("start() get list lich thi of giang vien has id = {}", id);

        List<Schedule> list = em.createNativeQuery("select * from schedule where (is_deleted != true or is_deleted is null) and (giam_thi_1_id = " + id + ") or (giam_thi_2_id = " + id + ")", Schedule.class).getResultList();
        log.info("finish() get list lich thi of giang vien has id = {}", id);
        return list;
    }

    //== method for algorithm ==
    @Override
    public List<Schedule> getListScheduleOfInstitute(String institute) {
        log.info("start() get list lich thi cua viện {}", institute);
        List<Schedule> scheduleList = em.createNativeQuery("SELECT * FROM schedule WHERE (is_deleted != true or is_deleted is null) and ten_vien = " + "'" + institute + "'", Schedule.class).getResultList();
        log.info("finish() get list lich thi cua viện {}", institute);
        return scheduleList;
    }

    @Override
    public List<Schedule> getListSchedulePaging(Integer page) {
        log.info("start() get paging schedules");
        List<Schedule> list = em.createNativeQuery("SELECT * FROM schedule WHERE  (is_deleted != true or is_deleted is null) order by lich_thi_id limit 25 offset " + (page - 1) * 25, Schedule.class).getResultList();
        log.info("finish() get paging schedules");
        return list;
    }

    @Override
    public List<String> getListInstitude() {
        List<String> institude = em.createNativeQuery("SELECT DISTINCT ten_vien FROM schedule WHERE (is_deleted != true or is_deleted is null)").getResultList();
        log.info("institude = {}", institude);
        return institude;
    }
}
