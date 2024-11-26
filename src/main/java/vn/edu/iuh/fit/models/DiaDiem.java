package vn.edu.iuh.fit.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "dia_diem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaDiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tenDiaDiem;

    private String diaChi;

    private String hinhAnh;

    @ManyToMany(mappedBy = "diaDiems") // Định nghĩa phía đối lập của quan hệ
    @JsonIgnore
    private List<Tour> tours;
}
