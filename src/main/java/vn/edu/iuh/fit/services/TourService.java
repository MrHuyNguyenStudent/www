package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.DiaDiem;
import vn.edu.iuh.fit.models.Tour;
import vn.edu.iuh.fit.repositoris.DiaDiemRepository;
import vn.edu.iuh.fit.repositoris.TourRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepository tourRepository;
    @Autowired
    private DiaDiemRepository diaDiemRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    // tao mơi tour
    public Tour createTour(String tenTour, String moTa, double gia, String hinhAnh, List<Integer> diaDiemIds) {
        // Tìm các địa điểm bằng ID
        List<DiaDiem> diaDiems = diaDiemRepository.findAllById(diaDiemIds);

        // Tạo đối tượng Tour mới
        Tour tour = new Tour();
        tour.setTenTour(tenTour);
        tour.setMoTa(moTa);
        tour.setGia(gia);
        tour.setHinhAnh(hinhAnh);
        tour.setDiaDiems(diaDiems);

        // Lưu tour vào cơ sở dữ liệu và trả về đối tượng Tour mới
        return tourRepository.save(tour);
    }
    // Phương thức sửa tour
    public Tour updateTour(int tourId, String tenTour, String moTa, double gia, String hinhAnh, List<Integer> diaDiemIds) {
        // Kiểm tra xem tour có tồn tại không
        Optional<Tour> optionalTour = tourRepository.findById(tourId);
        if (optionalTour.isEmpty()) {
            throw new RuntimeException("Tour không tìm thấy với id: " + tourId);
        }

        // Lấy đối tượng tour cần sửa
        Tour tour = optionalTour.get();

        // Cập nhật thông tin tour
        tour.setTenTour(tenTour);
        tour.setMoTa(moTa);
        tour.setGia(gia);
        tour.setHinhAnh(hinhAnh);

        // Tìm các địa điểm theo ID
        List<DiaDiem> diaDiems = diaDiemRepository.findAllById(diaDiemIds);
        if (diaDiems.size() != diaDiemIds.size()) {
            throw new RuntimeException("Một số địa điểm không tồn tại.");
        }

        // Cập nhật danh sách địa điểm vào tour
        tour.setDiaDiems(diaDiems);

        // Lưu lại tour sau khi sửa
        return tourRepository.save(tour);
    }
}