package thenhat.code.managerwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "giang_vien")
public class GiangVien{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "giang_vien_id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "bo_mon")
    private String boMon;

    @Column(name = "phong_lam_viec")
    private String phongLamViec;

    @Column(name = "trong_thi")
    private Boolean trongThi;

}
