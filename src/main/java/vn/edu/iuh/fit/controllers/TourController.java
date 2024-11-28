package vn.edu.iuh.fit.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.DTO.TourDTO;
import vn.edu.iuh.fit.models.Tour;
import vn.edu.iuh.fit.services.TourService;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }
    @PostMapping("/create")
    public ResponseEntity<Tour> createTour(@Valid @RequestBody TourDTO tourDTO) {
        try {
            // Tạo tour mới
            Tour newTour = tourService.createTour(
                    tourDTO.getTenTour(),
                    tourDTO.getMoTa(),
                    tourDTO.getGia(),
                    tourDTO.getHinhAnh(),
                    tourDTO.getDiaDiemIds()
            );
            return new ResponseEntity<>(newTour, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Tour> updateTour(
            @PathVariable("id") int tourId,
            @RequestBody TourDTO tourDTO
    ) {
        // Gọi service để sửa tour
        try {
            Tour updatedTour = tourService.updateTour(
                    tourId,
                    tourDTO.getTenTour(),
                    tourDTO.getMoTa(),
                    tourDTO.getGia(),
                    tourDTO.getHinhAnh(),
                    tourDTO.getDiaDiemIds()
            );
            return new ResponseEntity<>(updatedTour, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}