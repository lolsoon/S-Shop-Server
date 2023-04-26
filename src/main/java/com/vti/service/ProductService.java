package com.vti.service;

import com.vti.entity.Product;
import com.vti.form.ProductCreateForm;
import com.vti.form.ProductFilterForm;
import com.vti.form.ProductUpdateForm;
import com.vti.repository.IProductRepository;
import com.vti.specification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Product> findAll(Pageable pageable, ProductFilterForm form) {
        return repository.findAll(ProductSpecification.buildWhere(form), pageable);
    }

    @Override
    public Product findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(ProductCreateForm form) {
        TypeMap<ProductCreateForm, Product> typeMap = mapper.getTypeMap(ProductCreateForm.class, Product.class);
        if (typeMap == null) {
            mapper.addMappings(new PropertyMap<ProductCreateForm, Product>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        repository.save(mapper.map(form, Product.class));
    }

    @Override
    public void update(ProductUpdateForm form) {
        repository.save(mapper.map(form, Product.class));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        repository.deleteAllById(ids);
    }
}
