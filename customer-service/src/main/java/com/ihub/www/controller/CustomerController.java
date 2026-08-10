package com.ihub.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihub.www.entity.Customer;
import com.ihub.www.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/fetch")
	public List<Customer> getAllCustomers()
	{
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/fetch/{custId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long custId)
	{
		return customerService.getCustomerById(custId);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
	{
		return customerService.updateCustomer(customer);
	}
	
	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long custId)
	{
		return customerService.deleteCustomer(custId);
	}
	
	@PatchMapping("/patch/{custId}")
	public ResponseEntity<String> patchCustomer(@PathVariable Long custId, @RequestBody Map<String, Object> data)
	{
		return customerService.patchCustomer(custId,data);
	}
}
