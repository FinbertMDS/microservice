package com.ewolff.microservice.customer.api;

import com.ewolff.microservice.customer.Customer;
import com.ewolff.microservice.customer.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPIController {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerAPIController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
  
	@RequestMapping(value = "/", method = RequestMethod.GET)
  public Iterable<Customer> customerList() {
    return customerRepository.findAll();
  }

	@RequestMapping(value = "/", method = RequestMethod.POST)
  public Customer addCustomer(@RequestBody Customer newCustomer) {
    return customerRepository.save(newCustomer);
  }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable("id") long id) {
		return customerRepository.findById(id).get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Customer updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		customer.setId(id);
		customerRepository.save(customer);
		return customerRepository.findById(id).get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Customer deleteCustomer(@PathVariable("id") long id) {
    customerRepository.deleteById(id);
    return new Customer(id);
	}

}
