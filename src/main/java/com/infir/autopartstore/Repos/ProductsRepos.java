package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepos extends CrudRepository<Products,Long> {

    List<Products> findByTitleproductsContains(String titleproducts);

    List<Products> findByTitleproducts(String titleproducts);
}