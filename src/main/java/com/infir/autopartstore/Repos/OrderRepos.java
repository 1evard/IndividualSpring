package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepos extends CrudRepository<Order,Long> {
    List<Order> findByData(Date data);
}
