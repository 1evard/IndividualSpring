package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.ProductContent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductContentRepos extends CrudRepository<ProductContent,Long> {
    List<ProductContent> findByName(String name);
}
