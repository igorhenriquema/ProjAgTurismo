package com.turismo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.turismo.model.Place;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismo.repository.PlaceRepository;


@RestController
public class PlaceController {
	
	@Autowired
	PlaceRepository repository;
	
	//http://localhost:8080/place/save

	@PostMapping("place/save")
	public Place save(@RequestBody Place place){
		return repository.save(place);
	}
	
	//http://localhost:8080/place/all
	@GetMapping("place/all")
	public List<Place> all(){
		return (List<Place>) repository.findAll();
	}
	
	//http://localhost:8080/place/delete/2
	@DeleteMapping("place/delete/{id}")
	public boolean delete(@PathVariable("id") int id) {
		try {
			repository.deleteById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}