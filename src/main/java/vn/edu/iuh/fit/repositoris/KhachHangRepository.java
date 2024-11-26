package vn.edu.iuh.fit.repositoris;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.KhachHang;
@Repository
public interface KhachHangRepository extends CrudRepository<KhachHang, Integer> {
    KhachHang findByEmail(String email);
    KhachHang findBySoDienThoai(String soDienThoai);
}
