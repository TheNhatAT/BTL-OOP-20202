package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.GiangVien;
import thenhat.code.managerwebapp.model.LopHoc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class LopHocDAOImpl implements LopHocDAO {
    //== field ==
    @PersistenceContext
    EntityManager em;

    @Override
    public void addAllLopHoc(List<LopHoc> lopHocList) {
        log.info("start() add all lop hoc");
        for (LopHoc lopHoc : lopHocList) {
            em.persist(lopHoc);
        }
        log.info("finish() add all lop hoc");
        em.close();
    }

    //== methods ==
    @Override
    public void addLopHoc(LopHoc lopHoc) {
        log.info("start() add lop hoc = {}", lopHoc.toString());
        em.persist(lopHoc);
        log.info("finish() add lop hoc");
        em.close();
    }

    @Override
    public void addLopHoc(LopHoc lopHoc, Long id) {
        log.info("start() add lop hoc = {} with giang vien has id = {}", lopHoc.toString(), id);
        lopHoc.setGiangVien(em.find(GiangVien.class, id));
        em.persist(lopHoc);
        log.info("finish() add lop hoc with giang vien");
        em.close();
    }

    @Override
    public void updateLopHoc(LopHoc lopHoc) {
        log.info("start() update from lop hoc = {} to lop hoc = {}", em.find(LopHoc.class, lopHoc.getMaLop()), lopHoc);
        em.merge(lopHoc);
        log.info("finish() update lop hoc");
        em.close();
    }

    @Override
    public LopHoc getLopHocByMaLop(Long maLop) {
        log.info("start() get lop hoc which has ma lop = {}", maLop);
        LopHoc lopHoc = em.find(LopHoc.class, maLop);
        log.info("finish() get lop hoc");
        em.close();
        return lopHoc;
    }

    @Override
    public List<LopHoc> getAllLopHoc() {
        log.info("start() get all lop hoc");
        List<LopHoc> list = em.createNativeQuery("SELECT * FROM lop_hoc", LopHoc.class).getResultList();
        log.info("finish() get all lop hoc");
        em.close();
        return list;
    }

    @Override
    public void removeLopHoc(Long maLop) {
        log.info("start() remove lop hoc = {}", em.find(LopHoc.class, maLop));
        em.remove(em.find(LopHoc.class, maLop));
        log.info("finish() remove lop hoc");
        em.close();
    }

    @Override
    public List<LopHoc> getListLopHocOfGiangVienId(Long id) {
        log.info("start() get list lop hoc of giang vien has id = {}", id);
        List<LopHoc> list = em.createNativeQuery("SELECT * FROM lop_hoc WHERE giang_vien_id = " + id.toString(), LopHoc.class).getResultList();
        log.info("finish() get list lop hoc of {}", em.find(GiangVien.class, id).getName());
        return list;
    }
}
