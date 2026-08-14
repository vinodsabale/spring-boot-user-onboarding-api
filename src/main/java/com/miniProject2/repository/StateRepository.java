package com.miniProject2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject2.entity.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Integer>{
	List<StateEntity> findByCountryEntity_CountryId(Integer countryId);
	
}
