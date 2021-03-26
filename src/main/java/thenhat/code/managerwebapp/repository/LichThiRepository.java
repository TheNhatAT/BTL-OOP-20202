package thenhat.code.managerwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thenhat.code.managerwebapp.model.LichThi;

//-- JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods --
//-- update to use Hibernate after --
//-- this inteface is used by Spring Data JPA --
public interface LichThiRepository extends JpaRepository<LichThi, Long> {
//    void addLichThi(LichThi lichThi);
//    void editLichThiById(int id);
//    List<LichThi> listLichThi();
//    LichThi getLichThiById(int id);
//    void removeLichThiById(int id);
}
