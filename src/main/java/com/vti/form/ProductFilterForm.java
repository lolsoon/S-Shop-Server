package com.vti.form;

import com.vti.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProductFilterForm {
    private String search;
    private Integer categoryId;
    private Double minSalePrice;
    private Double maxSalePrice;
    private Product.Ram ram;
    private Integer minYear;
    private Integer maxYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxCreatedDate;
}
