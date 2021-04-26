package thenhat.code.managerwebapp.DAO;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.Class;
import thenhat.code.managerwebapp.model.Schedule;
import thenhat.code.managerwebapp.model.Teacher;

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
        em.close();
    }

    @Override
    public void addSchedule(Schedule schedule, Long id) {
        log.info("start() add lich thi in DAO = {} with 1 giam thi has id = {}", schedule.toString(), id);
        schedule.setGiamThi1(em.find(Teacher.class, id));
        em.persist(schedule);
        log.info("finish add lich thi with 1 giam thi");
        em.close();
    }

    @Override
    public void addSchedule(Schedule schedule, Long id_1, Long id_2) {
        log.info("start() add lich thi in DAO = {} with giam thi 1 has id_1 = {}, id_2 = {}", schedule.toString(), id_1, id_2);
        schedule.setGiamThi1(em.find(Teacher.class, id_1));
        schedule.setGiamThi2(em.find(Teacher.class, id_2));
        em.persist(schedule);
        log.info("finish add lich thi with giam thi");
        em.close();
    }

    @NonNull
    @Override
    public void addAllSchedules(List<Schedule> scheduleList) {
        for (Schedule lichthi :
                scheduleList) {
            em.persist(lichthi);
        }
        log.info("List Lich Thi saved");
        em.close();
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        em.merge(schedule);
        em.close();
        log.info("updated lich thi = {}", schedule);
    }

    @Override
    public List<Schedule> getListSchedule() {
        List<Schedule> scheduleList = em.createNativeQuery("SELECT * FROM schedule", Schedule.class).getResultList();
        em.close();
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
        em.remove(em.find(Schedule.class, id));
        em.close();
    }

    @Override
    public List<Schedule> getListScheduleOfCodeClass(Long maLop) {
        log.info("start() get list lich thi of lop hoc has ma lop = {}", maLop);
        List<Schedule> list = em.createNativeQuery("SELECT * FROM schedule WHERE ma_lop = " + maLop.toString(), Schedule.class).getResultList();
        log.info("finish() get list lich thi of lop hoc = {}");
        em.close();
        return list;
    }

    @Override
    public List<Schedule> getListScheduleOfTeacherId(Long id) {
        log.info("start() get list lich thi of giang vien has id = {}", id);
        List<Class> classList = em.createNativeQuery("SELECT * FROM class WHERE giang_vien_id = " + id.toString(), Class.class).getResultList();
        List<Schedule> list = new ArrayList<>();
        for (Class aClass : classList) {
            list.addAll(getListScheduleOfCodeClass(aClass.getMaLop()));
        }
        log.info("finish() get list lich thi of giang vien has id = {}", id);
        return list;
    }

    //== method for algorithm ==
    @Override
    public List<Schedule> getListScheduleOfInstitute(String institute) {
        log.info("start() get list lich thi cua viện {}", institute);
        List<Schedule> scheduleList = em.createNativeQuery("SELECT * FROM schedule WHERE ten_vien = " + "'" + institute + "'", Schedule.class).getResultList();
        log.info("finish() get list lich thi cua viện {}", institute);
        em.close();
        return scheduleList;
    }

    @Override
    public List<String> getListInstitude() {
        List<String> institude = em.createNativeQuery("SELECT DISTINCT ten_vien FROM schedule").getResultList();
        em.close();
        return institude;
    }
}
