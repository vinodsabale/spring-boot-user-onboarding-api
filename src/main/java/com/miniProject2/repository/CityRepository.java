package com.miniProject2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject2.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer>{
	public List<CityEntity> findByStateEntityStateId(Integer stateId);
}
