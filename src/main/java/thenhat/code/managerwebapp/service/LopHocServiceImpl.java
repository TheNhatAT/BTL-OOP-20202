package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.LopHocDAO;
import thenhat.code.managerwebapp.model.LopHoc;

import java.util.List;

@Slf4j
@Service
public class LopHocServiceImpl implements LopHocService {
    //== field ==
    LopHocDAO lopHocDAO;

    @Autowired
    public LopHocServiceImpl(LopHocDAO lopHocDAO) {
        this.lopHocDAO = lopHocDAO;
    }

    @Override
    public void addLopHoc(LopHoc lopHoc) {
        this.lopHocDAO.addLopHoc(lopHoc);
    }

    @Override
    public void updateLopHoc(LopHoc lopHoc) {
        this.lopHocDAO.updateLopHoc(lopHoc);
    }

    @Override
    public LopHoc getLopHocByMaLop(Long maLop) {
        return this.lopHocDAO.getLopHocByMaLop(maLop);
    }

    @Override
    public List<LopHoc> getAllLopHoc() {
        return this.lopHocDAO.getAllLopHoc();
    }

    @Override
    public void removeLopHoc(Long maLop) {
        this.lopHocDAO.removeLopHoc(maLop);
    }

    @Override
    public List<LopHoc> getListLopHocOfGiangVienId(Long id) {
        return this.lopHocDAO.getListLopHocOfGiangVienId(id);
    }
}
