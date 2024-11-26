package vn.edu.iuh.fit.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.DiaDiem;
import vn.edu.iuh.fit.services.DiaDiemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dia-diems")
public class DiaDiemController {

    @Autowired
    private DiaDiemService diaDiemService;

    // Lấy danh sách tất cả địa điểm
    @GetMapping
    public ResponseEntity<List<DiaDiem>> getAllDiaDiems() {
        List<DiaDiem> diaDiems = diaDiemService.getAllDiaDiems();
        return new ResponseEntity<>(diaDiems, HttpStatus.OK);
    }

    // Lấy địa điểm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DiaDiem> getDiaDiemById(@PathVariable int id) {
        Optional<DiaDiem> diaDiem = diaDiemService.getDiaDiemById(id);
        return diaDiem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới địa điểm
    @PostMapping
    public ResponseEntity<DiaDiem> createDiaDiem(@RequestBody DiaDiem diaDiem) {
        DiaDiem newDiaDiem = diaDiemService.createDiaDiem(diaDiem);
        return new ResponseEntity<>(newDiaDiem, HttpStatus.CREATED);
    }

    // Cập nhật địa điểm
    @PutMapping("/{id}")
    public ResponseEntity<DiaDiem> updateDiaDiem(@PathVariable int id, @RequestBody DiaDiem diaDiem) {
        DiaDiem updatedDiaDiem = diaDiemService.updateDiaDiem(id, diaDiem);
        return updatedDiaDiem != null ? new ResponseEntity<>(updatedDiaDiem, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    // Xóa địa điểm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiaDiem(@PathVariable int id) {
        diaDiemService.deleteDiaDiem(id);
        return ResponseEntity.noContent().build();
    }
}