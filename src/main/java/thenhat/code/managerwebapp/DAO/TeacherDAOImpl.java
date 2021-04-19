package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.Teacher;
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
        List<Teacher> list = em.createNativeQuery("SELECT * FROM teacher", Teacher.class).getResultList();
        log.info("finish() get all giang vien");
        em.close();
        return list;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        log.info("start() get giang vien which has id = {}", id);
        Teacher teacher = em.find(Teacher.class, id);
        log.info("finish() get giang vien = {}", teacher.toString());
        em.close();
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        log.info("start() update giang vien = {} to giang vien = {}", em.find(Teacher.class, teacher.getId()));
        em.merge(teacher);
        log.info("finish() update");
        em.close();
    }

    @Override
    public void addTeacher(Teacher teacher) {
        log.info("start() add giang vien = {}", teacher.toString());
        em.persist(teacher);
        log.info("finish() add");
        em.close();
    }

    @Override
    public void removeTeacherById(Long id) {
        log.info("start() remove giang vien = {} from DB", em.find(Teacher.class, id));
        em.remove(em.find(Teacher.class, id));
        em.close();
        log.info("finish() remove");
    }
}
