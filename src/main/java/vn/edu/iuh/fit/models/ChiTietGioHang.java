package vn.edu.iuh.fit.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chi_tiet_gio_hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietGioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id", nullable = false)
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(nullable = false)
    private int soLuong;

    @Column(nullable = false)
    private double donGia;

}
