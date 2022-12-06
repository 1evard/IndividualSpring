package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Basket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketRepos extends CrudRepository<Basket,Long> {
    List<Basket> findByQuantity(Long quantity);

}
