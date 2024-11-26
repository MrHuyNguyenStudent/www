package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.TaiKhoan;
import vn.edu.iuh.fit.repositoris.TaiKhoanRepository;
import vn.edu.iuh.fit.services.TaiKhoanService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tai-khoan")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    // API tạo mới tài khoản
    @PostMapping("/create")
    public ResponseEntity<TaiKhoan> createTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        TaiKhoan newTaiKhoan = taiKhoanService.createTaiKhoan(taiKhoan);
        return new ResponseEntity<>(newTaiKhoan, HttpStatus.CREATED);
    }

    // API lấy tất cả tài khoản
    @GetMapping("/all")
    public ResponseEntity<List<TaiKhoan>> getAllTaiKhoans() {
        List<TaiKhoan> taiKhoans = taiKhoanService.getAllTaiKhoans();
        return new ResponseEntity<>(taiKhoans, HttpStatus.OK);
    }

    // API lấy tài khoản theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable int id) {
        Optional<TaiKhoan> taiKhoan = taiKhoanService.getTaiKhoanById(id);
        return taiKhoan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // API sửa tài khoản
    @PutMapping("/update/{id}")
    public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable int id, @RequestBody TaiKhoan taiKhoan) {
        TaiKhoan updatedTaiKhoan = taiKhoanService.updateTaiKhoan(id, taiKhoan);
        return updatedTaiKhoan != null
                ? new ResponseEntity<>(updatedTaiKhoan, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // API xóa tài khoản
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaiKhoan(@PathVariable int id) {
        boolean isDeleted = taiKhoanService.deleteTaiKhoan(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // API tìm tài khoản theo username
    @GetMapping("/username/{username}")
    public ResponseEntity<TaiKhoan> getTaiKhoanByUsername(@PathVariable String username) {
        TaiKhoan taiKhoan = taiKhoanService.findByUsername(username);
        return taiKhoan != null
                ? new ResponseEntity<>(taiKhoan, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
