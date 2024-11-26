package vn.edu.iuh.fit.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.KhuyenMai;
@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Integer> {
    KhuyenMai findByMaKhuyenMai(String maKhuyenMai);
}
