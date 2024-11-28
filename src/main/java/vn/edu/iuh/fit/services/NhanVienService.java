package vn.edu.iuh.fit.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.NhanVien;
import vn.edu.iuh.fit.repositoris.NhanVienRepository;
@Service
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    public NhanVienService(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }
    public NhanVien themNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }
}
