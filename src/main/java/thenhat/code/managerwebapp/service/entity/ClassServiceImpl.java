package thenhat.code.managerwebapp.service.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.entity.ClassDAO;
import thenhat.code.managerwebapp.model.entity.Class;

import java.util.List;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {
    //== field ==
    ClassDAO classDAO;

    @Autowired
    public ClassServiceImpl(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public void addAllClass(List<Class> classList) {
        classDAO.addAllClass(classList);
    }

    @Override
    public void addClass(Class aClass) {
        this.classDAO.addClass(aClass);
    }

    @Override
    public void addClass(Class aClass, Long id) {
        this.classDAO.addClass(aClass, id);
    }

    @Override
    public void updateClass(Class aClass) {
        this.classDAO.updateClass(aClass);
    }

    @Override
    public Class getClassByCodeClass(Long maLop) {
        return this.classDAO.getClassByCodeClass(maLop);
    }

    @Override
    public List<Class> getAllClasses() {
        return this.classDAO.getAllClasses();
    }

    @Override
    public void removeClassByCode(Long maLop) {
        this.classDAO.removeClassByCode(maLop);
    }

    @Override
    public void removeClassById(Long id) {
        this.classDAO.removeClassById(id);
    }

    @Override
    public Class getClassById(Long id) {
        return this.classDAO.getClassById(id);
    }

    @Override
    public List<Class> getListTeacherPaging(Integer page) {
        return this.classDAO.getListClassPaging(page);
    }

    @Override
    public List<Class> getListClassOfTeacherId(Long id) {
        return this.classDAO.getListClassOfTeacherId(id);
    }
}
