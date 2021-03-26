package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.LopHoc;

import java.util.List;

public interface LopHocDAO {

    void addLopHoc(LopHoc lopHoc);

    void updateLopHoc(LopHoc lopHoc);

    LopHoc getLopHocByMaLop(Long maLop);

    List<LopHoc> getAllLopHoc();

    void removeLopHoc(Long maLop);

    List<LopHoc> getListLopHocOfGiangVienId(Long id);

}
