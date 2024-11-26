package vn.edu.iuh.fit.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tai_khoan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

}
