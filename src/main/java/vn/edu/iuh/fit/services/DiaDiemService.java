package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.DiaDiem;
import vn.edu.iuh.fit.repositoris.DiaDiemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiaDiemService {

    @Autowired
    private DiaDiemRepository diaDiemRepository;

    // Lấy tất cả địa điểm
    public List<DiaDiem> getAllDiaDiems() {
        return diaDiemRepository.findAll();
    }

    // Lấy địa điểm theo id
    public Optional<DiaDiem> getDiaDiemById(int id) {
        return diaDiemRepository.findById(id);
    }

    // Tạo mới một địa điểm
    public DiaDiem createDiaDiem(DiaDiem diaDiem) {
        return diaDiemRepository.save(diaDiem);
    }

    // Cập nhật địa điểm
    public DiaDiem updateDiaDiem(int id, DiaDiem diaDiem) {
        if (diaDiemRepository.existsById(id)) {
            diaDiem.setId(id);
            return diaDiemRepository.save(diaDiem);
        }
        return null;
    }

    // Xóa địa điểm
    public void deleteDiaDiem(int id) {
        diaDiemRepository.deleteById(id);
    }
}