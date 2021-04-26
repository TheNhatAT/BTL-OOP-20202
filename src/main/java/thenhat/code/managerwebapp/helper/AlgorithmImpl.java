package thenhat.code.managerwebapp.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thenhat.code.managerwebapp.DAO.TeacherDAO;
import thenhat.code.managerwebapp.model.Assigment;
import thenhat.code.managerwebapp.model.Schedule;
import thenhat.code.managerwebapp.model.Teacher;
import thenhat.code.managerwebapp.DAO.ScheduleDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
public class AlgorithmImpl implements Algorithm{
    TeacherDAO teacherDAO;
    ScheduleDAO scheduleDAO;

    @Autowired
    public AlgorithmImpl(ScheduleDAO scheduleDAO, TeacherDAO teacherDAO) {
        this.scheduleDAO = scheduleDAO;
        this.teacherDAO = teacherDAO;
    }
    @Override
    public boolean checkTeacher(Long CanBo1Id, Long MaLopThi) {
        Schedule lichthi = scheduleDAO.getScheduleById(MaLopThi);
        List<Schedule> listLtGV = scheduleDAO.getListScheduleOfTeacherId(CanBo1Id);
        for (int i = 0; i < listLtGV.size(); ++i) {
            if (lichthi.getKipThi().equals(listLtGV.get(i).getKipThi()) &&
                    lichthi.getNgayThi().equals(listLtGV.get(i).getNgayThi())) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean checkTeacher(Long CanBo1Id, Long CanBo2Id, Long MaLopThi) {
        if (checkTeacher(CanBo1Id, MaLopThi) && checkTeacher(CanBo2Id, MaLopThi)) {
            return true;
        }
        return false;
    }

    @Override
    public Vector<Integer> checkImportFile() {
        Vector<Integer> error = new Vector<Integer>(2);
        List<Teacher> listTeacher = teacherDAO.getAllTeacher();
        List<Schedule> listSchedule = scheduleDAO.getListSchedule();
        int len = listSchedule.size();
        for (int i = 0; i < len; ++i) {
            Schedule obj1 = listSchedule.get(i);
            for (int j = i + 1; j < len; ++j) {
                Schedule obj2 = listSchedule.get(j);
                if (obj1.getNgayThi().equals(obj2.getNgayThi()) && obj1.getKipThi().equals(obj2.getKipThi())) {
                    if (obj1.getSoLuongDk() < 60 && obj1.getSoLuongDk() < 60) {
                        if (obj1.getGiamThi1().equals(obj2.getGiamThi1())) {
                            error.add(obj1.getMaLop(), obj2.getMaLop());
                            return error;
                        }
                    } else {
                        if (obj1.getGiamThi1().equals(obj2.getGiamThi1()) ||
                                obj1.getGiamThi1().equals(obj2.getGiamThi2()) ||
                                obj1.getGiamThi2().equals(obj2.getGiamThi1()) ||
                                obj1.getGiamThi2().equals(obj2.getGiamThi2())) {
                            error.add(obj1.getMaLop(), obj2.getMaLop());
                            return error;
                        }
                    }
                }
            }
        }
        return null;
    }
    private boolean check(int indexClass, int indexTeacher, List<Schedule> listSchedule, List<Teacher> listTeacher){
        return true;
    }
    @Override
    public List<Assigment> AutoAssignment() {

        List<Assigment> result = new ArrayList<Assigment>();
        List<String> institude = scheduleDAO.getListInstitude();
        for (int i = 0; i < institude.size(); ++i) {
            String ins = institude.get(i);
            List<Teacher> listTeacher = teacherDAO.getListTeacherOfInstitute(ins);
            List<Schedule> listSchedule = scheduleDAO.getListScheduleOfInstitute(ins);
            List<ArrayList<Integer>> storeSche = new ArrayList<ArrayList<Integer>>();
            String kipTemp = null;
            String dateTemp = null;
            int count = -1;
            for (int j = 0; j < listSchedule.size(); ++j) {
                String kip = listSchedule.get(j).getKipThi();
                String date = listSchedule.get(j).getNgayThi();
                if (kip.equals(kipTemp) && date.equals(dateTemp)) {
                    storeSche.get(count).add(j);
                } else {
                    kipTemp = kip;
                    dateTemp = date;
                    ArrayList<Integer> object = new ArrayList<Integer>();
                    object.add(j);
                    storeSche.add(object);
                    count += 1;
                }
            }
            int teacherTemp = 0;
            for (int j = 0; j < storeSche.size(); ++j) {
                for (int index = 0; index < storeSche.get(j).size(); ++index) {
                    Assigment t = new Assigment();
                    Schedule sche = listSchedule.get(storeSche.get(j).get(index));
                    t.setSchedules_id(sche.getId());
                    t.setCanbo1(listTeacher.get(teacherTemp).getId());
                    teacherTemp = (teacherTemp + 1)% listTeacher.size();
                    if (sche.getSoLuongDk() >= 60) {
                        t.setCanbo2(listTeacher.get(teacherTemp).getId());
                        teacherTemp = (teacherTemp + 1)% listTeacher.size();
                    }
                    result.add(t);
                }
            }
        }
        return result;
    }
}
