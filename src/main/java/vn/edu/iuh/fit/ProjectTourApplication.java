package vn.edu.iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.edu.iuh.fit.services.TaiKhoanService;

@SpringBootApplication
public class ProjectTourApplication implements CommandLineRunner {

    @Autowired
    private TaiKhoanService taiKhoanService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectTourApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
