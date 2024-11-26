package vn.edu.iuh.fit.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.GioHang;
import vn.edu.iuh.fit.models.KhachHang;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    GioHang findByKhachHang(KhachHang khachHang);
}
