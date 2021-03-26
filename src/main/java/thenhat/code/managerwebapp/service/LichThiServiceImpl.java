package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.LichThiDAO;
import thenhat.code.managerwebapp.model.LichThi;

import java.util.List;

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


}
