package com.vti.form;

import com.vti.validation.CategoryNotExistsByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCreateForm {
    @NotBlank(message = "{CategoryForm.name.NotBlank}")
    @Length(max = 50, message = "{CategoryForm.name.Length}")
    @CategoryNotExistsByName
    private String name;
}
