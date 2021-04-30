package thenhat.code.managerwebapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "class")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Class {

    @Id
    @Column(name = "ma_lop")
    private Long maLop;

    @Column(name = "ky_hoc")
    private String kyHoc;

    @Column(name = "ten_lop")
    private String tenLop;

    @Column(name = "so_luong_sinh_vien")
    private Integer soLuongSV;

    @Column(name = "to_chuc_thi")
    private Boolean toChucThi;

    @Column(name = "kinh_phi")
    private Double kinhPhi;

    @ManyToOne
    @JoinColumn(name = "giang_vien_id", foreignKey = @ForeignKey(name = "giang_vien_id_fk"))
    private Teacher teacher;
}
