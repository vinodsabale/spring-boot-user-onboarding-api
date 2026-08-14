package com.miniProject2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject2.dto.ApiResponseDto;
import com.miniProject2.dto.CityDto;
import com.miniProject2.dto.CountryDto;
import com.miniProject2.dto.QuoteApiResponseDto;
import com.miniProject2.dto.RestPwdDto;
import com.miniProject2.dto.StateDto;
import com.miniProject2.dto.UserDto;
import com.miniProject2.service.UserService;

@RestController
public class UserRestController {
	private final UserService userService;

	UserRestController(UserService userService) {
		this.userService = userService;
	}
	 
	@GetMapping("/countries")
	public ResponseEntity<ApiResponseDto<List<CountryDto>>> getCountries(){
		ApiResponseDto<List<CountryDto>> response=new ApiResponseDto<>();
		List<CountryDto> countries=userService.getCountries();
		if(countries.isEmpty()) {
			response.setStatus(500);
			response.setMessage("No Countries Found");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			response.setStatus(200);
			response.setMessage("Fetched Countries Successfully !");
			response.setData(countries);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
	}
	@GetMapping("/states/{countryId}")
	public ResponseEntity<ApiResponseDto<List<StateDto>>> getStates(@PathVariable Integer countryId){
		ApiResponseDto<List<StateDto>> response=new ApiResponseDto<>();
		List<StateDto> state=userService.getStates(countryId);
		if(state.isEmpty()) {
			response.setStatus(500);
			response.setMessage("No States Found");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			response.setStatus(200);
			response.setMessage("Fetched States Successfully !");
			response.setData(state);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}		
	}
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<ApiResponseDto<List<CityDto>>> getCity(@PathVariable Integer stateId){
		ApiResponseDto<List<CityDto>> response=new ApiResponseDto<>();
		List<CityDto> city=userService.getCity(stateId);
		if(city.isEmpty()) {
			response.setStatus(500);
			response.setMessage("No City Found");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			response.setStatus(200);
			response.setMessage("Fetched City Successfully !");
			response.setData(city);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}		
	}
	@GetMapping("/unique/{email}")
	public ResponseEntity<ApiResponseDto<String>> checkEmail(@PathVariable String email)
	{
		ApiResponseDto<String> response=new ApiResponseDto<>();
		boolean isUnique=userService.isEmailUnique(email);
		if(!isUnique) {
			response.setStatus(200);
			response.setMessage("Duplicate Email Found");
			response.setData("DUPLICATE");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(200);
			response.setMessage("No Email Found");
			response.setData("UNIQUE");
			  return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	@PostMapping("/user")
	public ResponseEntity<ApiResponseDto<String>> registerUser(@RequestBody UserDto userDto){
		ApiResponseDto<String> response=new ApiResponseDto<>();
		boolean isRegistered=userService.register(userDto);
		if(isRegistered) {
			response.setStatus(200);
			response.setMessage("Registration successfull !");
			response.setData("SUCCESS");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setData("FAILED");
			response.setMessage("Failed to Register");
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponseDto<UserDto>> login(@RequestBody UserDto userDto){
		ApiResponseDto<UserDto> response=new ApiResponseDto<>();
		UserDto user=userService.login(userDto.getEmail(), userDto.getPassword());
		if(user!=null) {
			response.setStatus(200);
			response.setMessage("Login Success");
			response.setData(user);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(400);
			response.setMessage("Invalid Credentials");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/rest-pwd")
	public ResponseEntity<ApiResponseDto<String>> restPassword(@RequestBody RestPwdDto restpwd){
		ApiResponseDto<String> response=new ApiResponseDto<>();
		boolean isPwdUpdate=userService.restPassword(restpwd);
		if(isPwdUpdate) {
			response.setStatus(200);
			response.setMessage("Password update");
			response.setData("SUCCESS");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(400);
			response.setMessage("Record not found");
			response.setData("FAILED");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/quotes")
	public ResponseEntity<ApiResponseDto<QuoteApiResponseDto>> getQuotation(){
		ApiResponseDto<QuoteApiResponseDto> response=new ApiResponseDto<>();
		QuoteApiResponseDto quote=userService.getQuote();
		if(quote!=null) {
			response.setStatus(200);
			response.setMessage("Quote fetched successfully");
			response.setData(quote);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("Quote fetched successfully");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
