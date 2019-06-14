package com.turismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turismo.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> { }