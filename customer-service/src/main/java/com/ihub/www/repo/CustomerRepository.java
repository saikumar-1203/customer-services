package com.ihub.www.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihub.www.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> 
{

}
