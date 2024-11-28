package vn.edu.iuh.fit.DTO;

import lombok.Data;

@Data
public class OtpVerificationRequest {
    private String phone;
    private String otp;
}