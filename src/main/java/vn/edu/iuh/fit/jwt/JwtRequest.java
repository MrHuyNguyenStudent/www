package vn.edu.iuh.fit.jwt;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
    private String token;
    private String refreshToken;
}
