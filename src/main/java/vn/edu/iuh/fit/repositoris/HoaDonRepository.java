package vn.edu.iuh.fit.repositoris;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.HoaDon;
import vn.edu.iuh.fit.models.KhachHang;
import vn.edu.iuh.fit.models.NhanVien;

import java.util.List;
@Repository
public interface HoaDonRepository extends CrudRepository<HoaDon, Integer> {
    List<HoaDon> findByKhachHang(KhachHang khachHang);
    List<HoaDon> findByNhanVien(NhanVien nhanVien);
}
