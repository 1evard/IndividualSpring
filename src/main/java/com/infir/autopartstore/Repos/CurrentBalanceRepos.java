package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.CurrentBalance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrentBalanceRepos extends CrudRepository<CurrentBalance,Long> {
    List<CurrentBalance> findByQuantity(Long quantity);
}
