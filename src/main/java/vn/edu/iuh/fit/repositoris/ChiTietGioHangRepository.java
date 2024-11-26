package vn.edu.iuh.fit.repositoris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.ChiTietGioHang;
import vn.edu.iuh.fit.models.GioHang;

import java.util.List;

@Repository
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
    List<ChiTietGioHang> findByGioHang(GioHang gioHang);
}