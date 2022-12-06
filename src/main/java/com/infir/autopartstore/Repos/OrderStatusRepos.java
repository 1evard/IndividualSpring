package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderStatusRepos extends CrudRepository<OrderStatus,Long> {
    List<OrderStatus> findByName(String name);
}
