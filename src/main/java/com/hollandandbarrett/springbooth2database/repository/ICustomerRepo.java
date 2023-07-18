package com.hollandandbarrett.springbooth2database.repository;

import com.hollandandbarrett.springbooth2database.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
}
