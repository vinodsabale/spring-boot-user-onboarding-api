package com.miniProject2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="state_master")
public class StateEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer stateId;
	private String stateName;
	@ManyToOne
	@JoinColumn(name="countryId")
	private CountryEntity countryEntity;
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public CountryEntity getCountryEntity() {
		return countryEntity;
	}
	public void setCountryEntity(CountryEntity countryEntity) {
		this.countryEntity = countryEntity;
	}
	
}
