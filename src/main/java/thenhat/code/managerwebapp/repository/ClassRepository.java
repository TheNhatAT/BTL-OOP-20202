package thenhat.code.managerwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thenhat.code.managerwebapp.model.entity.Schedule;

//-- JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods --
//-- update to use Hibernate after --
//-- this inteface is used by Spring Data JPA --
public interface ClassRepository extends JpaRepository<Schedule, Long> {
//    void addLichThi(LichThi lichThi);
//    void editLichThiById(int id);
//    List<LichThi> listLichThi();
//    LichThi getLichThiById(int id);
//    void removeScheduleById(int id);
}
