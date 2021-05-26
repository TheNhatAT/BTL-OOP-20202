package thenhat.code.managerwebapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    //== fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lich_thi_id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "ten_vien")
    private String tenVien;

    @Column(name = "ma_lop")
    private Integer maLop;

    @Column(name = "ma_hp")
    private String maHp;

    @Column(name = "ten_hp")
    private String tenHp;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ten_nhom")
    private String tenNhom;

    @Column(name = "dot_mo")
    private String dotMo;

    @Column(name = "tuan")
    private String tuan;

    @Column(name = "thu")
    private String thu;

    @Column(name = "ngay_thi")
    private String ngayThi;

    @Column(name = "kip_thi")
    private String kipThi;

    @Column(name = "so_luong_dk")
    private Integer soLuongDk;

    @Column(name = "phong_thi")
    private String phongThi;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    //-- cần sử dụng one to one để liên kết với giám thị trông thi --
    //-- nâng cấp lúc sau --
    @ManyToOne
    @JoinColumn(name = "giam_thi_1_id", foreignKey = @ForeignKey(name = "giam_thi_1_fk"))
    private Teacher GiamThi1;

    @ManyToOne
    @JoinColumn(name = "giam_thi_2_id", foreignKey = @ForeignKey(name = "giam_thi_2_fk"))
    private Teacher GiamThi2;

    //== for thymeleaf ==
    @Transient
    private Long giamThi1ID;

    @Transient
    private Long giamThi2ID;
}
