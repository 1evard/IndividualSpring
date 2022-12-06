package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepos extends JpaRepository<Employee,Long> {

}