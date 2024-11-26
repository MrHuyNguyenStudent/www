package vn.edu.iuh.fit.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tour")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tenTour;

    private String moTa;

    private double gia;

    private String hinhAnh;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tour_dia_diem", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "tour_id"), // Khóa ngoại tham chiếu từ bảng Tour
            inverseJoinColumns = @JoinColumn(name = "dia_diem_id") // Khóa ngoại tham chiếu từ bảng DiaDiem
    )
    @JsonManagedReference
    private List<DiaDiem> diaDiems;
}
