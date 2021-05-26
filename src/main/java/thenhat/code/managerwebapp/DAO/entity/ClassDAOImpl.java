package thenhat.code.managerwebapp.DAO.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.entity.Teacher;
import thenhat.code.managerwebapp.model.entity.Class;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class ClassDAOImpl implements ClassDAO {
    //== field ==
    @PersistenceContext
    EntityManager em;

    //== methods ==
    @Override
    public void addAllClass(List<Class> classList) {
        log.info("start() add all lop hoc");
        for (Class aClass : classList) {
            em.persist(aClass);
        }
        log.info("finish() add all lop hoc");
    }

    @Override
    public void addClass(Class aClass) {
        log.info("start() add lop hoc = {}", aClass.toString());
        em.persist(aClass);
        log.info("finish() add lop hoc");
    }

    @Override
    public void addClass(Class aClass, Long id) {
        log.info("start() add lop hoc = {} with giang vien has id = {}", aClass.toString(), id);
        aClass.setTeacher(em.find(Teacher.class, id));
        em.persist(aClass);
        log.info("finish() add lop hoc with giang vien");
    }

    @Override
    public void updateClass(Class aClass) {
        log.info("start() update from lop hoc = {} to lop hoc = {}", em.find(Class.class, aClass.getMaLop()), aClass);
        em.merge(aClass);
        log.info("finish() update lop hoc");
    }

    @Override
    public Class getClassByCodeClass(Long maLop) {
        log.info("start() get lop hoc which has ma lop = {}", maLop);
        Class aClass = (Class) em.createNativeQuery("SELECT * FROM class WHERE (is_deleted != true or is_deleted is null) and ma_lop = " + maLop, Class.class).getSingleResult();
        log.info("finish() get lop hoc");
        return aClass;
    }

    @Override
    public List<Class> getAllClasses() {
        log.info("start() get all lop hoc");
        List<Class> list = em.createNativeQuery("SELECT * FROM class WHERE (is_deleted != true or is_deleted is null)", Class.class).getResultList();
        log.info("finish() get all lop hoc");
        return list;
    }

    @Override
    public void removeClassByCode(Long maLop) {
        log.info("start() remove lop hoc");
        Class aClass = (Class) em.createNativeQuery("SELECT * FROM class WHERE ma_lop = " + maLop, Class.class).getSingleResult();
        aClass.setIsDeleted(true);
        em.merge(aClass);
        log.info("finish() remove lop hoc");
    }

    @Override
    public void removeClassById(Long id) {
        log.info("start() remove lop hoc");
        Class aClass = em.find(Class.class, id);
        aClass.setIsDeleted(true);
        em.merge(aClass);
        log.info("finish() remove lop hoc");
    }

    @Override
    public List<Class> getListClassOfTeacherId(Long id) {
        log.info("start() get list lop hoc of giang vien has id = {}", id);
        List<Class> list = em.createNativeQuery("SELECT * FROM class WHERE (is_deleted != true or is_deleted is null) and giang_vien_id = " + id, Class.class).getResultList();
        log.info("finish() get list lop hoc");
        return list;
    }

    @Override
    public Class getClassById(Long id) {
        log.info("start() get class by id");
        Class aClass = (Class) em.createNativeQuery("SELECT * FROM class WHERE (is_deleted != true or is_deleted is null) and class_id = " + id, Class.class).getSingleResult();
        log.info("finish() get class by id");
        return aClass;
    }
}
