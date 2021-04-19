package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void removeTeacherById(Long id);
    List<Teacher> getListTeacherOfInstitute(String institute);
}
