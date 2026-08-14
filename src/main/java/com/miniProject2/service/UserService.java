package com.miniProject2.service;

import java.util.List;

import com.miniProject2.dto.CityDto;
import com.miniProject2.dto.CountryDto;
import com.miniProject2.dto.QuoteApiResponseDto;
import com.miniProject2.dto.RestPwdDto;
import com.miniProject2.dto.StateDto;
import com.miniProject2.dto.UserDto;

public interface UserService {
    public List<CountryDto> getCountries();
    public List<StateDto> getStates(Integer countryId);
    public List<CityDto> getCity(Integer stateId);
	public boolean isEmailUnique(String email);
     public boolean register(UserDto userDto);
     public UserDto login(String email,String password);
     public boolean restPassword(RestPwdDto restPwsDto);
    public QuoteApiResponseDto getQuote();
}
