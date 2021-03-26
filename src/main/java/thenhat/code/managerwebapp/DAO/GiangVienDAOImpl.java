package thenhat.code.managerwebapp.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.GiangVien;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class GiangVienDAOImpl implements GiangVienDAO {

    //== field ==
    @PersistenceContext
    EntityManager em;

    //== methods ==
    @Override
    public List<GiangVien> getAllGiangVien() {
        log.info("start() get all giang vien");
        List<GiangVien> list = em.createNativeQuery("SELECT * FROM giang_vien", GiangVien.class).getResultList();
        log.info("finish() get all giang vien");
        em.close();
        return list;
    }

    @Override
    public GiangVien getGiangVienById(Long id) {
        log.info("start() get giang vien which has id = {}", id);
        GiangVien giangVien = em.find(GiangVien.class, id);
        log.info("finish() get giang vien = {}", giangVien.toString());
        em.close();
        return giangVien;
    }

    @Override
    public void updateGiangVien(GiangVien giangVien) {
        log.info("start() update giang vien = {} to giang vien = {}", em.find(GiangVien.class, giangVien.getId()));
        em.merge(giangVien);
        log.info("finish() update");
        em.close();
    }

    @Override
    public void addGiangVien(GiangVien giangVien) {
        log.info("start() add giang vien = {}", giangVien.toString());
        em.persist(giangVien);
        log.info("finish() add");
        em.close();
    }

    @Override
    public void removeGiangVienById(Long id) {
        log.info("start() remove giang vien = {} from DB", em.find(GiangVien.class, id));
        em.remove(em.find(GiangVien.class, id));
        em.close();
        log.info("finish() remove");
    }
}
