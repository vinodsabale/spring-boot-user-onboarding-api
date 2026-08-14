package com.miniProject2.dto;

public class UserDto {
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private Long phno;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	public UserDto() {}
	public UserDto(Integer userId, String name, String email, String password, Long phno, Integer countryId,
			Integer stateId, Integer cityId) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phno = phno;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
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
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phno=" + phno + ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId + "]";
	}
	
	
	
}
