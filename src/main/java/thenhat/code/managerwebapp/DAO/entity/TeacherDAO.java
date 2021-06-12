package thenhat.code.managerwebapp.DAO.entity;

import thenhat.code.managerwebapp.model.entity.Teacher;

import java.util.List;

public interface TeacherDAO {

    List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void removeTeacherById(Long id);

    List<Teacher> getListTeacherOfInstitute(String institute);

    List<Teacher> getListTeacherPaging(Integer page);
}
