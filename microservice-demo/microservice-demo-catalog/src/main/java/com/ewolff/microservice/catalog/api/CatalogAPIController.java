package com.ewolff.microservice.catalog.api;

import javax.servlet.http.HttpServletResponse;

import com.ewolff.microservice.catalog.Item;
import com.ewolff.microservice.catalog.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogAPIController {

	private final ItemRepository itemRepository;

	@Autowired
	public CatalogAPIController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Item> itemList() {
		return itemRepository.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Item addItem(@RequestBody Item newItem) {
		return itemRepository.save(newItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Item getItem(@PathVariable("id") long id, HttpServletResponse response) {
		return itemRepository.findById(id).get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Item updateItem(@PathVariable("id") long id, @RequestBody Item customer) {
		customer.setId(id);
		itemRepository.save(customer);
		return itemRepository.findById(id).get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItem(@PathVariable("id") long id) {
		itemRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	public Iterable<Item> search(@RequestParam("query") String query) {
		return itemRepository.findByNameContaining(query);
	}

}
