package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.TaiKhoan;
import vn.edu.iuh.fit.repositoris.TaiKhoanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    // Tạo mới một tài khoản
    public TaiKhoan createTaiKhoan(TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }

    // Lấy tất cả tài khoản
    public List<TaiKhoan> getAllTaiKhoans() {
        return taiKhoanRepository.findAll();
    }

    // Lấy tài khoản theo ID
    public Optional<TaiKhoan> getTaiKhoanById(int id) {
        return taiKhoanRepository.findById(id);
    }

    // Cập nhật tài khoản
    public TaiKhoan updateTaiKhoan(int id, TaiKhoan taiKhoan) {
        if (taiKhoanRepository.existsById(id)) {
            taiKhoan.setId(id);
            return taiKhoanRepository.save(taiKhoan);
        }
        return null;
    }

    // Xóa tài khoản
    public boolean deleteTaiKhoan(int id) {
        if (taiKhoanRepository.existsById(id)) {
            taiKhoanRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tìm tài khoản theo username
    public TaiKhoan findByUsername(String username) {
        return taiKhoanRepository.findByUsername(username);
    }
}