package thenhat.code.managerwebapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thenhat.code.managerwebapp.DAO.GiangVienDAO;
import thenhat.code.managerwebapp.model.GiangVien;

import java.util.List;

@Slf4j
@Service
public class GiangVienServiceImpl implements GiangVienService{
    //== field ==
    GiangVienDAO giangVienDAO;

    //== constructor injection ==
    @Autowired
    public GiangVienServiceImpl(GiangVienDAO giangVienDAO) {
        this.giangVienDAO = giangVienDAO;
    }

    //== method ==
    @Override
    public List<GiangVien> getAllGiangVien() {
        return this.giangVienDAO.getAllGiangVien();
    }

    @Override
    public GiangVien getGiangVienById(Long id) {
        return this.giangVienDAO.getGiangVienById(id);
    }

    @Override
    public void updateGiangVien(GiangVien giangVien) {
        this.giangVienDAO.updateGiangVien(giangVien);
    }

    @Override
    public void addGiangVien(GiangVien giangVien) {
        this.giangVienDAO.addGiangVien(giangVien);
    }

    @Override
    public void removeGiangVienById(Long id) {
        this.giangVienDAO.removeGiangVienById(id);
    }
}
