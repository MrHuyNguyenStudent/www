package vn.edu.iuh.fit.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.DTO.LoginRequest;
import vn.edu.iuh.fit.DTO.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    @Autowired
//    private AuthService authService;
//
//    @Operation(summary = "Đăng nhập với tên người dùng và mật khẩu")
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
//        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
//    }
}
