package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.LopHoc;

import java.util.List;

public interface LopHocService {
    void addAllLopHoc(List<LopHoc> lopHocList);

    void addLopHoc(LopHoc lopHoc);

    void updateLopHoc(LopHoc lopHoc);

    LopHoc getLopHocByMaLop(Long maLop);

    List<LopHoc> getAllLopHoc();

    void removeLopHoc(Long maLop);

    List<LopHoc> getListLopHocOfGiangVienId(Long id);
}
