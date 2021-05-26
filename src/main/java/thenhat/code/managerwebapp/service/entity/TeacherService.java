package thenhat.code.managerwebapp.service.entity;

import thenhat.code.managerwebapp.model.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void removeTeacherById(Long id);

    List<Teacher> getListTeacherOfInstitute(String institute);
}
