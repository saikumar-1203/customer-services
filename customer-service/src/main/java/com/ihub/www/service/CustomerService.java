package com.ihub.www.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ihub.www.entity.Customer;
import com.ihub.www.repo.CustomerRepository;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public ResponseEntity<Customer> getCustomerById(Long custId)
    {
        if (!customerRepository.existsById(custId))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = customerRepository.findById(custId).get();

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    public ResponseEntity<Customer> updateCustomer(Customer customer)
    {
        if (!customerRepository.existsById(customer.getCustId()))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer old_cust =
                customerRepository.findById(customer.getCustId()).get();

        old_cust.setCustName(customer.getCustName());
        old_cust.setCustAddress(customer.getCustAddress());

        Customer updatedCustomer = customerRepository.save(old_cust);

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCustomer(Long custId)
    {
        if (!customerRepository.existsById(custId))
        {
            return new ResponseEntity<>(
                    "Customer Not Found",
                    HttpStatus.NOT_FOUND);
        }

        customerRepository.deleteById(custId);

        return new ResponseEntity<>(
                "Record Deleted",
                HttpStatus.OK);
    }

    public ResponseEntity<String> patchCustomer(
            Long custId,
            Map<String, Object> data)
    {
        if (!customerRepository.existsById(custId))
        {
            return new ResponseEntity<>(
                    "Patch Failed",
                    HttpStatus.NOT_FOUND);
        }

        Customer customer = customerRepository.findById(custId).get();

        data.forEach((key, value) ->
        {
            switch (key)
            {
                case "custName":
                    customer.setCustName((String) value);
                    break;

                case "custAddress":
                    customer.setCustAddress((String) value);
                    break;
            }
        });

        customerRepository.save(customer);

        return new ResponseEntity<>(
                "Record Updated",
                HttpStatus.OK);
    }
}
/*
package com.ihub.www.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ihub.www.entity.Customer;
import com.ihub.www.repo.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}
	public List<Customer> getAllCustomers()
	{
		return customerRepository.findAll();
	}
	public ResponseEntity<Customer> getCustomerById(Long custId)
	{
		Customer customer = customerRepository.findById(custId).get();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	public ResponseEntity<Customer> updateCustomer(Customer customer)
	{
		Customer old_cust= customerRepository.findById(customer.getCustId()).get();
		old_cust.setCustName(customer.getCustName());
		old_cust.setCustAddress(customer.getCustAddress());
		customerRepository.save(old_cust);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	public ResponseEntity<String> deleteCustomer(Long custId)
	{
		Customer customer=customerRepository.findById(custId).get();
		customerRepository.delete(customer);
		return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
	}
	public ResponseEntity<String> patchCustomer(Long custId, Map<String, Object> data)
	{
		Customer customer=customerRepository.findById(custId).get();
		if(customer==null)
		{
			return new ResponseEntity<>("Patch Failed",HttpStatus.NOT_FOUND);
		}
		else
		{
			data.forEach((key,value)->{
				switch(key)
				{
					case "custId": customer.setCustId((Long)value); break;
					case "custName" : customer.setCustName((String)value); break;
					case "custAddress" : customer.setCustAddress((String)value); break;
				}
			});
			customerRepository.save(customer);
			return new ResponseEntity<>("Record Updated",HttpStatus.OK);
		}
		
		
	}
}
*/