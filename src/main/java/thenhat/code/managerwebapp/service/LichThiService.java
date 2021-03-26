package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.LichThi;

import java.util.List;

public interface LichThiService {
    void addLichThi(LichThi lichThi);

    void addAllLichThi(List<LichThi> lichThiList);

    void updateLichThi(LichThi lichThi);

    List<LichThi> getListLichThi();

    LichThi getLichThiById(Long id);

    void removeLichThiById(Long id);

     List<LichThi> getListLichThiOfLopHocMaLop(Long maLop);

     List<LichThi> getListLichThiOfGiangVienId(Long id);
}
