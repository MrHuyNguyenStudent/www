package vn.edu.iuh.fit.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourDTO {
    private String tenTour;
    private String moTa;
    private double gia;
    private String hinhAnh;
    private List<Integer> diaDiemIds; // List of DiaDiem IDs
}
