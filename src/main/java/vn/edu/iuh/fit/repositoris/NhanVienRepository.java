package vn.edu.iuh.fit.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.NhanVien;
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    NhanVien findByEmail(String email);
    NhanVien findBySoDienThoai(String soDienThoai);
}
