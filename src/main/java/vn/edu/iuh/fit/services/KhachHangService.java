package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.KhachHang;
import vn.edu.iuh.fit.repositoris.KhachHangRepository;

@Service
public class KhachHangService {

    private final KhachHangRepository khachHangRepository;

    @Autowired
    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    public KhachHang themKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }
}
