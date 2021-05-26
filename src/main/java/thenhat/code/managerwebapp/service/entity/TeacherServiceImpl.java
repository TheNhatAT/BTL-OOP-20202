package thenhat.code.managerwebapp.service.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.entity.TeacherDAO;
import thenhat.code.managerwebapp.model.entity.Teacher;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {
    //== field ==
    TeacherDAO teacherDAO;

    //== constructor injection ==
    @Autowired
    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    //== method ==
    @Override
    public List<Teacher> getAllTeachers() {
        return this.teacherDAO.getAllTeacher();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return this.teacherDAO.getTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        this.teacherDAO.updateTeacher(teacher);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        this.teacherDAO.addTeacher(teacher);
    }

    @Override
    public void removeTeacherById(Long id) {
        this.teacherDAO.removeTeacherById(id);
    }

    @Override
    public List<Teacher> getListTeacherOfInstitute(String institute) {
        return this.teacherDAO.getListTeacherOfInstitute(institute);
    }
}
