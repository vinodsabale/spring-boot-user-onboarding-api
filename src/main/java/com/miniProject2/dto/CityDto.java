package com.miniProject2.dto;

public class CityDto {
	private Integer cityId;
	private String cityName;
	public CityDto() {}
	


	public CityDto(Integer cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}

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
	@Override
	public String toString() {
		return "CityDto [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
}
