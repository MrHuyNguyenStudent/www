package vn.edu.iuh.fit.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.DiaDiem;
@Repository
public interface DiaDiemRepository extends JpaRepository<DiaDiem, Integer> {
    DiaDiem findByTenDiaDiem(String tenDiaDiem);
}
