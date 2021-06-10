package thenhat.code.managerwebapp.DAO.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.entity.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class TeacherDAOImpl implements TeacherDAO {

    //== field ==
    @PersistenceContext
    EntityManager em;

    //== methods ==
    @Override
    public List<Teacher> getAllTeacher() {
        log.info("start() get all giang vien");
        List<Teacher> list = em.createNativeQuery("SELECT * FROM teacher WHERE (is_deleted != true or is_deleted is null) order by teacher_id", Teacher.class).getResultList();
        log.info("finish() get all giang vien");
        return list;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        log.info("start() get giang vien which has id = {}", id);
        Teacher teacher = em.find(Teacher.class, id);
        log.info("finish() get giang vien = {}", teacher.toString());
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        log.info("start() update giang vien = {} to giang vien = {}", em.find(Teacher.class, teacher.getId()));
        em.merge(teacher);
        log.info("finish() update");
    }

    @Override
    public void addTeacher(Teacher teacher) {
        log.info("start() add giang vien = {}", teacher.toString());
        em.persist(teacher);
        log.info("finish() add");
    }

    @Override
    public void removeTeacherById(Long id) {
        log.info("start() remove giang vien = {} from DB", em.find(Teacher.class, id));
        Teacher teacher = em.find(Teacher.class, id);
        teacher.setIsDeleted(true);
        em.merge(teacher);
        log.info("finish() remove");
    }

    //== method for algorithm ==
    @Override
    public List<Teacher> getListTeacherOfInstitute(String institute) {
        log.info("start() get list giang vien cua vien {}", institute);
        List<Teacher> teacherList = em.createNativeQuery("SELECT * FROM teacher WHERE (is_deleted != true or is_deleted is null) and vien = " + "'" + institute + "'", Teacher.class).getResultList();
        em.close();
        log.info("start() get list giang vien cua vien {}", institute);
        return teacherList;
    }
}
