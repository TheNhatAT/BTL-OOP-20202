package thenhat.code.managerwebapp.helper;

import thenhat.code.managerwebapp.model.entity.Assigment;

import java.util.List;
import java.util.Vector;

//== thuật toán xử lý xung đột thời gian ==
//-- phân tập giảng viên ứng với lịch thi (theo bộ môn) -> xử lý từng tập nhỏ --
public interface Algorithm {

    //-- theo dang viet API --
    //== truy vấn danh sánh viện ==
    //== lấy danh sách giảng viên của mỗi viện ==
    //== lấy danh sách lớp thi ứng với mỗi viện ==

    boolean checkTeacher(Long CanBo1Id, Long MaLopThi);
    boolean  checkTeacher(Long CanBo1Id, Long CanBo2Id, Long MaLopThi);
    Vector<Integer> checkImportFile();
    List<Assigment> AutoAssignment();
}
