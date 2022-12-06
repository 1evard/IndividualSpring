package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Stamp;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StampRepos extends CrudRepository<Stamp,Long> {
    List<Stamp> findByTitlestamp(String titlestamp);
}
