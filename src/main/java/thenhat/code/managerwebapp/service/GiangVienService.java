package thenhat.code.managerwebapp.service;

import thenhat.code.managerwebapp.model.GiangVien;

import java.util.List;

public interface GiangVienService {
    List<GiangVien> getAllGiangVien();

    GiangVien getGiangVienById(Long id);

    void updateGiangVien(GiangVien giangVien);

    void addGiangVien(GiangVien giangVien);

    void removeGiangVienById(Long id);
}
