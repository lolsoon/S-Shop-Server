package com.vti.specification;

import com.vti.entity.Category;
import com.vti.entity.Category_;
import com.vti.entity.Product;
import com.vti.entity.Product_;
import com.vti.form.CategoryFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.time.LocalDate;

public class CategorySpecification {
    public static Specification<Category> buildWhere(CategoryFilterForm form) {
        if (form == null) {
            return null;
        }
        return hasNameLike(form.getSearch())
                .or(hasProductNameLike(form.getSearch()))
                .and(hasCreatedDateGreaterThanOrEqualTo(form.getMinCreatedDate()))
                .and(hasCreatedDateLessThanOrEqualTo(form.getMaxCreatedDate()));
    }

    private static Specification<Category> hasProductNameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            Join<Category, Product> join = root.join(Category_.products, JoinType.LEFT);
            return builder.like(join.get(Product_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Category> hasNameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Category_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Category> hasCreatedDateGreaterThanOrEqualTo(LocalDate minCreatedDate) {
        return (root, query, builder) -> {
            if (minCreatedDate == null) {
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get(Category_.createdDate).as(LocalDate.class), minCreatedDate);
        };
    }

    private static Specification<Category> hasCreatedDateLessThanOrEqualTo(LocalDate maxCreatedDate) {
        return (root, query, builder) -> {
            if (maxCreatedDate == null) {
                return null;
            }
            return builder.lessThanOrEqualTo(root.get(Category_.createdDate).as(LocalDate.class), maxCreatedDate);
        };
    }
}
