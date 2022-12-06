package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Stamp;
import com.infir.autopartstore.Models.StampCar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StampCarRepos extends CrudRepository<StampCar,Long> {
    List<StampCar> findByTitlestampcar(String titlestampcar);
}
