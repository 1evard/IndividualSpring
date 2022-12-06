package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepos extends CrudRepository<ProductCategory,Long> {
    List<ProductCategory> findByName(String name);
}
