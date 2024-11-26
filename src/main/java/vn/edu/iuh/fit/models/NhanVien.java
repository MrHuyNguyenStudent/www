package vn.edu.iuh.fit.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String hoTen;

    @Column(nullable = false)
    private String soDienThoai;

    @Column(nullable = false, unique = true)
    private String email;

    private String diaChi;

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan", nullable = false)
    private TaiKhoan taiKhoan;

}
