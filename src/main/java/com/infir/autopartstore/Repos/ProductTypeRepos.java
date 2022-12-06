package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductTypeRepos extends CrudRepository<ProductType,Long> {
    List<ProductType> findByName(String name);
}
