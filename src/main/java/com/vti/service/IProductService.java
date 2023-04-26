package com.vti.service;

import com.vti.entity.Product;
import com.vti.form.ProductCreateForm;
import com.vti.form.ProductFilterForm;
import com.vti.form.ProductUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable, ProductFilterForm form);

    Product findById(int id);

    void create(ProductCreateForm form);

    void update(ProductUpdateForm form);

    void deleteById(int id);

    void deleteAll(List<Integer> ids);
}
