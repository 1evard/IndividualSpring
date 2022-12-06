package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Manufacturer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManufacturerRepos extends CrudRepository<Manufacturer,Long> {
    List<Manufacturer> findByName(String name);
}
