package com.vti.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO extends RepresentationModel<CategoryDTO> {
    private int id;
    private String name;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;
    private List<ProductDTO> products;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductDTO extends RepresentationModel<ProductDTO> {
        private int id;
        private String name;
        private double price;
        private double salePrice;
        private String thumbnailUrl;
        private String description;
        private String ram;
        private LocalDate createdDate;
        private LocalDateTime updatedAt;
    }
}
