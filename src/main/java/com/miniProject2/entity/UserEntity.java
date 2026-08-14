package com.miniProject2.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="user_master")
@Entity

public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String updatePassword;
	private Long phno;
	@ManyToOne
	@JoinColumn(name="countryId")
	private CountryEntity countryEntity;
	@ManyToOne
	@JoinColumn(name="stateId")	
	private StateEntity stateEntity;
	@ManyToOne
	@JoinColumn(name="cityId")
	private CityEntity cityEntity;
	@CreationTimestamp
	private LocalDate createdAt;
	@UpdateTimestamp
	private LocalDate updatedAt;
	
	public String getUpdatePassword() {
		return updatePassword;
	}
	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	public CountryEntity getCountryEntity() {
		return countryEntity;
	}
	public void setCountryEntity(CountryEntity countryEntity) {
		this.countryEntity = countryEntity;
	}
	public StateEntity getStateEntity() {
		return stateEntity;
	}
	public void setStateEntity(StateEntity stateEntity) {
		this.stateEntity = stateEntity;
	}
	public CityEntity getCityEntity() {
		return cityEntity;
	}
	public void setCityEntity(CityEntity cityEntity) {
		this.cityEntity = cityEntity;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
