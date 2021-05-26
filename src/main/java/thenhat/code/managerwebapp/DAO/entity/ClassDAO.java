package thenhat.code.managerwebapp.DAO.entity;

import thenhat.code.managerwebapp.model.entity.Class;

import java.util.List;

public interface ClassDAO {

    void addAllClass(List<Class> classList);

    void addClass(Class aClass);

    void addClass(Class aClass, Long id);

    void updateClass(Class aClass);

    Class getClassByCodeClass(Long maLop);

    List<Class> getAllClasses();

    void removeClassByCode(Long maLop);

    void removeClassById(Long id);

    List<Class> getListClassOfTeacherId(Long id);

    Class getClassById(Long id);

}
