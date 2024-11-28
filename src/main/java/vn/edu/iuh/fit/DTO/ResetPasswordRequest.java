package vn.edu.iuh.fit.DTO;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String phone;
    private String newPassword;
}