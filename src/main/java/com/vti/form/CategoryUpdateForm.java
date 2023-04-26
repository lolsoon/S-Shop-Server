package com.vti.form;

import com.vti.validation.CategoryExistsById;
import com.vti.validation.CategoryNotExistsByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryUpdateForm {
    @NotNull(message = "{CategoryForm.id.NotNull}")
    @CategoryExistsById
    private Integer id;

    @NotBlank(message = "{CategoryForm.name.NotBlank}")
    @Length(max = 50, message = "{CategoryForm.name.Length}")
    @CategoryNotExistsByName
    private String name;
}
