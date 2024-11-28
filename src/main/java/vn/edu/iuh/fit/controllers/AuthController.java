package vn.edu.iuh.fit.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.DTO.*;
import vn.edu.iuh.fit.models.KhachHang;
import vn.edu.iuh.fit.models.NhanVien;
import vn.edu.iuh.fit.models.TaiKhoan;
import vn.edu.iuh.fit.repositoris.OTPRepository;
import vn.edu.iuh.fit.repositoris.TaiKhoanRepository;
import vn.edu.iuh.fit.services.JwtService;
import vn.edu.iuh.fit.services.KhachHangService;
import vn.edu.iuh.fit.services.NhanVienService;
import vn.edu.iuh.fit.services.TaiKhoanService;
import org.springframework.security.core.GrantedAuthority;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    TaiKhoanService taiKhoanService;
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    NhanVienService nhanVienService;
    OtpRequest otpRequest;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(null, userDetails);

            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("UNKNOWN");

            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken, role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
    @PostMapping("/register/khachhang")
    public ResponseEntity<?> registerKhachHang(@RequestBody SignUpDTO signUpDTO) {
        TaiKhoan existingTaiKhoan = taiKhoanService.findByUsername(signUpDTO.getUsername());
        if (existingTaiKhoan != null) {
            return ResponseEntity.badRequest().body("Username đã tồn tại.");
        }
        KhachHang existingKhachHang = khachHangService.findBySDT(signUpDTO.getPhone());
        if (existingKhachHang != null) {
            return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại.");
        }
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setUsername(signUpDTO.getUsername());
        taiKhoan.setPassword(encodedPassword);
        taiKhoan.setRoles("CUSTOMER");
        taiKhoanService.createTaiKhoan(taiKhoan);
        KhachHang khachHang = new KhachHang();
        khachHang.setDiaChi(signUpDTO.getDiaChi());
        khachHang.setEmail(signUpDTO.getEmail());
        khachHang.setSoDienThoai(signUpDTO.getPhone());
        khachHang.setHoTen(signUpDTO.getHoTen());
        khachHang.setTaiKhoan(taiKhoan);
        khachHangService.themKhachHang(khachHang);
        return ResponseEntity.ok("Đăng ký thành công!");
    }
    @PostMapping("/register/nhanvien")
    public ResponseEntity<?> registerNhanVien(@RequestBody SignUpDTO signUpDTO) {
        TaiKhoan existingTaiKhoan = taiKhoanService.findByUsername(signUpDTO.getUsername());
        if (existingTaiKhoan != null) {
            return ResponseEntity.badRequest().body("Username đã tồn tại.");
        }
        KhachHang existingKhachHang = khachHangService.findBySDT(signUpDTO.getPhone());
        if (existingKhachHang != null) {
            return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại.");
        }
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setUsername(signUpDTO.getUsername());
        taiKhoan.setPassword(encodedPassword);
        taiKhoan.setRoles("EMPLOYEE");
        taiKhoanService.createTaiKhoan(taiKhoan);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setDiaChi(signUpDTO.getDiaChi());
        nhanVien.setEmail(signUpDTO.getEmail());
        nhanVien.setSoDienThoai(signUpDTO.getPhone());
        nhanVien.setHoTen(signUpDTO.getHoTen());
        nhanVien.setTaiKhoan(taiKhoan);
        nhanVienService.themNhanVien(nhanVien);
        return ResponseEntity.ok("Đăng ký thành công!");
    }
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOTP(@RequestBody OtpRequest otpRequest) {
        String phone = otpRequest.getPhone();
        if (khachHangService.findBySDT(phone) != null) {
            return ResponseEntity.badRequest().body("Số điện thoại đã được sử dụng");
        }
        String otp = otpRepository.generateOTP(phone);
        log.info("OTP cho số điện thoại {} là: {}", phone, otp);
        return ResponseEntity.ok(otp);
    }
    @PostMapping("/confirm-otp")
    public ResponseEntity<?> confirmOTP(@RequestBody OtpVerificationRequest otpRequest) {
        String phone = otpRequest.getPhone();
        String otp = otpRequest.getOtp();
        if (!otpRepository.validateOTP(phone, otp)) {
            return ResponseEntity.badRequest().body("OTP không hợp lệ hoặc đã hết hạn.");
        }
        return ResponseEntity.ok("Xác thực OTP thành công!");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            TaiKhoan taiKhoan = taiKhoanService.findByUsername(username);
            if (taiKhoan == null) {
                return ResponseEntity.status(404).body("Tài khoản không tồn tại");
            }
            if (!passwordEncoder.matches(request.getOldPassword(), taiKhoan.getPassword())) {
                return ResponseEntity.badRequest().body("Mật khẩu cũ không chính xác");
            }
            taiKhoan.setPassword(passwordEncoder.encode(request.getNewPassword()));
            taiKhoanService.updateTaiKhoan(taiKhoan.getId(), taiKhoan);
            return ResponseEntity.ok("Đổi mật khẩu thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Đã xảy ra lỗi khi đổi mật khẩu");
        }
    }
    @PostMapping("/forgot-password/request-otp")
    public ResponseEntity<?> requestForgotPasswordOTP(@RequestBody OtpRequest otpRequest) {
        String phone = otpRequest.getPhone();
        KhachHang khachHang = khachHangService.findBySDT(phone);
        if (khachHang == null) {
            return ResponseEntity.badRequest().body("Số điện thoại không tồn tại trong hệ thống.");
        }
        String otp = otpRepository.generateOTP(phone);
        log.info("OTP cho số điện thoại {} là: {}", phone, otp);

        return ResponseEntity.ok(otp);
    }

    @PostMapping("/forgot-password/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        String phone = request.getPhone();
        String newPassword = request.getNewPassword();
        KhachHang khachHang = khachHangService.findBySDT(phone);
        if (khachHang == null) {
            return ResponseEntity.status(404).body("Tài khoản không tồn tại.");
        }
        TaiKhoan taiKhoan = khachHang.getTaiKhoan();
        if (taiKhoan == null) {
            return ResponseEntity.status(404).body("Tài khoản không tồn tại.");
        }
        taiKhoan.setPassword(passwordEncoder.encode(newPassword));
        taiKhoanService.updateTaiKhoan(taiKhoan.getId(),taiKhoan);

        return ResponseEntity.ok("Đặt lại mật khẩu thành công.");
    }
}
