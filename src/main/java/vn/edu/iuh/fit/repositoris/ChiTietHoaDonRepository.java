package vn.edu.iuh.fit.repositoris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.ChiTietHoaDon;
import vn.edu.iuh.fit.models.HoaDon;

import java.util.List;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Integer> {
    List<ChiTietHoaDon> findByHoaDon(HoaDon hoaDon);
}