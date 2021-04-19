package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.Teacher;

import java.util.List;

public interface TeacherDAO {

    List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void removeTeacherById(Long id);
}
