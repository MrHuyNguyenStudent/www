package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "chi_tiet_hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(nullable = false)
    private int soLuong;

    @Column(nullable = false)
    private double donGia;

    @ManyToOne
    @JoinColumn(name = "ma_khuyen_mai")
    private KhuyenMai khuyenMai;

}
