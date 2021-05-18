package thenhat.code.managerwebapp.service.entity;

import thenhat.code.managerwebapp.model.entity.Class;

import java.util.List;

public interface ClassService {
    void addAllClass(List<Class> classList);

    void addClass(Class aClass);

    void addClass(Class aClass, Long id);

    void updateClass(Class aClass);

    List<Class> getClassByCodeClass(Long maLop);

    List<Class> getAllClasses();

    void removeClass(Long maLop);

    List<Class> getListClassOfTeacherId(Long id);
}
