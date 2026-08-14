package com.miniProject2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="city_master")
@Entity
public class CityEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cityId;
	private String cityName;
	@ManyToOne
	@JoinColumn(name="stateId")
	private StateEntity stateEntity;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public StateEntity getStateEntity() {
		return stateEntity;
	}
	public void setStateEntity(StateEntity stateEntity) {
		this.stateEntity = stateEntity;
	}

}
