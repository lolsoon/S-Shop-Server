package com.vti.form;

import com.vti.entity.Product;
import com.vti.validation.CategoryExistsById;
import com.vti.validation.ProductExistsById;
import com.vti.validation.ProductNotExistsByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateForm {
    @NotNull(message = "{ProductForm.id.NotNull}")
    @ProductExistsById
    private Integer id;

    @NotBlank(message = "{ProductForm.name.NotBlank}")
    @Length(max = 50, message = "{ProductForm.name.Length}")
    @ProductNotExistsByName
    private String name;

    @NotNull(message = "{ProductForm.price.NotNull}")
    @PositiveOrZero(message = "{ProductForm.price.PositiveOrZero}")
    private Double price;

    @NotNull(message = "{ProductForm.salePrice.NotNull}")
    @PositiveOrZero(message = "{ProductForm.salePrice.PositiveOrZero}")
    private Double salePrice;

    @NotBlank(message = "{ProductForm.thumbnailUrl.NotBlank}")
    @Length(max = 255, message = "{ProductForm.thumbnailUrl.Length}")
    private String thumbnailUrl;

    @NotNull(message = "{ProductForm.ram.NotNull}")
    private Product.Ram ram;

    @NotBlank(message = "{ProductForm.description.NotBlank}")
    @Length(max = 1023, message = "{ProductForm.description.Length}")
    private String description;

    @NotNull(message = "{CategoryForm.id.NotNull}")
    @CategoryExistsById
    private Integer categoryId;
}
