package thenhat.code.managerwebapp.DAO;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thenhat.code.managerwebapp.model.GiangVien;
import thenhat.code.managerwebapp.model.LichThi;
import thenhat.code.managerwebapp.model.LopHoc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@Transactional
//-- use Hibernate|JPA --
public class LichThiDAOImpl implements LichThiDAO {

    //== fields ==
    @PersistenceContext
    private EntityManager em;

    //== REST methods ==
    @Override
    public void addLichThi(LichThi lichThi) {
        log.info("start() add lich thi in DAO = {}", lichThi.toString());
        em.persist(lichThi);
        log.info("finish add lich thi");
        em.close();
    }

    @Override
    public void addLichThi(LichThi lichThi, Long id) {
        log.info("start() add lich thi in DAO = {} with 1 giam thi has id = {}", lichThi.toString(), id);
        lichThi.setGiamThi1(em.find(GiangVien.class, id));
        em.persist(lichThi);
        log.info("finish add lich thi with 1 giam thi");
        em.close();
    }

    @Override
    public void addLichThi(LichThi lichThi, Long id_1, Long id_2) {
        log.info("start() add lich thi in DAO = {} with giam thi 1 has id_1 = {}, id_2 = {}", lichThi.toString(), id_1, id_2);
        lichThi.setGiamThi1(em.find(GiangVien.class, id_1));
        lichThi.setGiamThi2(em.find(GiangVien.class, id_2));
        em.persist(lichThi);
        log.info("finish add lich thi with giam thi");
        em.close();
    }

    @NonNull
    @Override
    public void addAllLichThi(List<LichThi> lichThiList) {
        for (LichThi lichthi :
                lichThiList) {
            em.persist(lichthi);
        }
        log.info("List Lich Thi saved");
        em.close();
    }

    @Override
    public void updateLichThi(LichThi lichThi) {
        em.merge(lichThi);
        em.close();
        log.info("updated lich thi = {}", lichThi);
    }

    @Override
    public List<LichThi> getListLichThi() {
        List<LichThi> lichThiList = em.createNativeQuery("SELECT * FROM lich_thi", LichThi.class).getResultList();
        em.close();
        log.info("Select all Lich Thi elements in DB");
        return lichThiList;
    }

    @Override
    public LichThi getLichThiById(Long id) {
        LichThi lichThi = em.find(LichThi.class, id);
        em.close();
        return lichThi;
    }

    @Override
    public void removeLichThiById(Long id) {
        em.remove(em.find(LichThi.class, id));
        em.close();
    }

    @Override
    public List<LichThi> getListLichThiOfLopHocMaLop(Long maLop) {
        log.info("start() get list lich thi of lop hoc has ma lop = {}", maLop);
        List<LichThi> list = em.createNativeQuery("SELECT * FROM lich_thi WHERE ma_lop = " + maLop.toString(), LichThi.class).getResultList();
        log.info("finish() get list lich thi of lop hoc = {}");
        em.close();
        return list;
    }

    @Override
    public List<LichThi> getListLichThiOfGiangVienId(Long id) {
        log.info("start() get list lich thi of giang vien has id = {}", id);
        List<LopHoc> lopHocList = em.createNativeQuery("SELECT * FROM lop_hoc WHERE giang_vien_id = " + id.toString(), LopHoc.class).getResultList();
        List<LichThi> list = new ArrayList<>();
        for (LopHoc lopHoc: lopHocList) {
            list.addAll(getListLichThiOfLopHocMaLop(lopHoc.getMaLop()));
        }
        return list;
    }
}
