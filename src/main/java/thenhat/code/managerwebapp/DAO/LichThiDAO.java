package thenhat.code.managerwebapp.DAO;

import thenhat.code.managerwebapp.model.LichThi;

import java.util.List;

//-- this interface is used by Hibernate --
public interface LichThiDAO {

    void addLichThi(LichThi lichThi);

    void addLichThi(LichThi lichThi, Long id);

    void addLichThi(LichThi lichThi, Long id_1, Long id_2);

    void addAllLichThi(List<LichThi> lichThiList);

    void updateLichThi(LichThi lichThi);

    List<LichThi> getListLichThi();

    LichThi getLichThiById(Long id);

    void removeLichThiById(Long id);

    List<LichThi> getListLichThiOfLopHocMaLop(Long maLop);

    List<LichThi> getListLichThiOfGiangVienId(Long id);
}
