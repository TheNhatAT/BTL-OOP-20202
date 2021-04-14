package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.LichThiDAO;
import thenhat.code.managerwebapp.model.GiangVien;
import thenhat.code.managerwebapp.model.LichThi;

import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

@Slf4j
@Service
public class LichThiServiceImpl implements LichThiService {

    //== fields ==
    private LichThiDAO lichThiDAO;

    //== constructor injection ==
    @Autowired
    public LichThiServiceImpl(LichThiDAO lichThiDAO) {
        this.lichThiDAO = lichThiDAO;
    }

    //== methods ==
    @Override
    public void addLichThi(LichThi lichThi) {
        this.lichThiDAO.addLichThi(lichThi);
    }

    @Override
    public void addLichThi(LichThi lichThi, Long id) {
        this.lichThiDAO.addLichThi(lichThi, id);
    }

    @Override
    public void addLichThi(LichThi lichThi, Long id_1, Long id_2) {
        this.lichThiDAO.addLichThi(lichThi, id_1, id_2);
    }

    @Override
    public void addAllLichThi(List<LichThi> lichThiList) {
        this.lichThiDAO.addAllLichThi(lichThiList);
    }

    @Override
    public void updateLichThi(LichThi lichThi) {
        this.lichThiDAO.updateLichThi(lichThi);
    }

    @Override
    public List<LichThi> getListLichThi() {
        return this.lichThiDAO.getListLichThi();
    }

    @Override
    public LichThi getLichThiById(Long id) {
        return this.lichThiDAO.getLichThiById(id);
    }

    @Override
    public void removeLichThiById(Long id) {
        this.lichThiDAO.removeLichThiById(id);
    }

    @Override
    public List<LichThi> getListLichThiOfLopHocMaLop(Long maLop) {
       return this.lichThiDAO.getListLichThiOfLopHocMaLop(maLop);
    }

    @Override
    public List<LichThi> getListLichThiOfGiangVienId(Long id) {
        return this.lichThiDAO.getListLichThiOfGiangVienId(id);
    }

    @Override
    // checkGiangVien se tra ve cap ma lich thi sap xep loi khi co giao vien bi trung
    public Vector<Integer> checkGiangVien() {
        List<LichThi> list = new ArrayList<LichThi>();
        list = this.lichThiDAO.getListLichThi();
        for (int i = 0; i < list.size(); ++i) {
            LichThi object1 = list.get(i);
            for (int j = i + 1; j < list.size(); ++j) {
                LichThi object2 = list.get(j);
                if (!object1.getTuan().equals(object2.getTuan()) &&
                        !object1.getThu().equals(object2.getThu())) {
                    continue;
                }
                if (object1.getGiamThi1().equals(object2.getGiamThi1()) &&
                        object1.getGiamThi1().equals(object2.getGiamThi2()) &&
                        object1.getGiamThi2().equals(object2.getGiamThi1()) &&
                        object1.getGiamThi2().equals(object2.getGiamThi2()) ) {
                    Vector<Integer> error = new Vector<Integer>();
                    error.add(object1.getMaLop());
                    error.add(object2.getMaLop());
                    return error;
                }
            }
        }
        Vector<Integer> success = new Vector<Integer>();
        success.add(0);
        success.add(0);
        return success;
    }
    @Override
    public Vector<Integer> checkSV() {
        List<LichThi> list = new ArrayList<LichThi>();
        for (int i = 0; i < list.size(); ++i) {
            LichThi object = list.get(i);
            if (object.getSoLuongDk() >= 60) {
                if (object.getGiamThi1() == null || object.getGiamThi2() == null) {
                    Vector<Integer> error = new Vector<Integer>();
                    error.add(list.get(i).getMaLop());
                    error.add(list.get(i).getMaLop());
                    return error;
                }
            }
        }
        Vector<Integer> success = new Vector<Integer>();
        success.add(0);
        success.add(0);
        return success;
    }
}
