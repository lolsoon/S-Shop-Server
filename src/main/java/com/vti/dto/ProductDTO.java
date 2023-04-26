package com.vti.dto;

import com.vti.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends RepresentationModel<ProductDTO> {
    private int id;
    private String name;
    private double price;
    private double salePrice;
    private String thumbnailUrl;
    private String description;
    private Product.Ram ram;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;
    private CategoryDTO category;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CategoryDTO extends RepresentationModel<CategoryDTO> {
        private int id;
        private String name;
        private LocalDate createdDate;
        private LocalDateTime updatedAt;
    }
}
