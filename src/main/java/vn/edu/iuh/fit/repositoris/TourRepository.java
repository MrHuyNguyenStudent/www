package vn.edu.iuh.fit.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Tour;
@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    @Query("SELECT t FROM Tour t JOIN FETCH t.diaDiems WHERE t.id = :id")
    Tour findTourWithDiaDiems(@Param("id") int id);
}
