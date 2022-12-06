package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Cheque;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChequeRepos extends CrudRepository<Cheque,Long> {
    List<Cheque> findByQuantity(Long quantity);

}