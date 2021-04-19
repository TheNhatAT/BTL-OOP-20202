package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.Class;

import java.util.List;

public interface ClassDAO {

    void addAllClass(List<Class> classList);

    void addClass(Class aClass);

    void addClass(Class aClass, Long id);

    void updateClass(Class aClass);

    Class getClassByCodeClass(Long maLop);

    List<Class> getAllClasses();

    void removeClass(Long maLop);

    List<Class> getListClassOfTeacherId(Long id);

}
