package com.miniProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject2.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer>{

}
