package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepos extends JpaRepository<Client,Long> {
}