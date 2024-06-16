package site.sugarnest.backend.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDto {
    private Double minPrice;    // Giá tối thiểu
    private Double maxPrice;    // Giá tối đa
    private List<String> suppliers; // Nhà sản xuất
    private List<String> categories; // Loại sản phẩm
    private String sortBy;      // Thuộc tính sắp xếp
    private String sortDirection; // Hướng sắp xếp (ASC/DESC)
}

