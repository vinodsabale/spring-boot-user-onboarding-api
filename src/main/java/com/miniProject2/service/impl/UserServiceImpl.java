package com.miniProject2.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miniProject2.dto.CityDto;
import com.miniProject2.dto.CountryDto;
import com.miniProject2.dto.QuoteApiResponseDto;
import com.miniProject2.dto.RestPwdDto;
import com.miniProject2.dto.StateDto;
import com.miniProject2.dto.UserDto;
import com.miniProject2.entity.CityEntity;
import com.miniProject2.entity.CountryEntity;
import com.miniProject2.entity.StateEntity;
import com.miniProject2.entity.UserEntity;
import com.miniProject2.repository.CityRepository;
import com.miniProject2.repository.CountryRepository;
import com.miniProject2.repository.StateRepository;
import com.miniProject2.repository.UserRepository;
import com.miniProject2.service.EmailService;
import com.miniProject2.service.TranslationService;
import com.miniProject2.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	   private final TranslationService translationService;
	    private final CountryRepository countryRepo;
	    private final StateRepository stateRepo;
	    private final UserRepository userRepo;
	    private final CityRepository cityRepo;
	    private final ModelMapper mapper;
	    private final EmailService emailService;

	    public UserServiceImpl(TranslationService translationService,
	                           EmailService emailService,
	                           CountryRepository countryRepo,
	                           UserRepository userRepo,
	                           StateRepository stateRepo,
	                           CityRepository cityRepo,
	                           ModelMapper mapper) {
	        this.translationService = translationService;
	        this.countryRepo = countryRepo;
	        this.stateRepo = stateRepo;
	        this.cityRepo = cityRepo;
	        this.mapper = mapper;
	        this.userRepo = userRepo;
	        this.emailService = emailService;
	    }
	@Override
	public List<CountryDto> getCountries() {
	 List<CountryEntity> countries=countryRepo.findAll();	 
		return countries.stream().map(country->mapper.map(country, CountryDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<StateDto> getStates(Integer countryId) {
		List<StateEntity> states=stateRepo.findByCountryEntity_CountryId(countryId);
		return states.stream().map(state->mapper.map(state, StateDto.class)).collect(Collectors.toList());	 
	}

	@Override 
	public List<CityDto> getCity(Integer stateId) {
		List<CityEntity> byStateStateId = cityRepo.findByStateEntityStateId(stateId);
		return byStateStateId.stream().map(city->mapper.map(city, CityDto.class)).collect(Collectors.toList());
	}

	@Override
	public boolean isEmailUnique(String email) {
		return !userRepo.existsByEmail(email);
	}

	@Override
	public boolean register(UserDto userDto) {
		UserEntity user=mapper.map(userDto, UserEntity.class);
		user.setPassword(generateRandomPassword(6));
		user.setUpdatePassword("NO");
		 
		CountryEntity country = countryRepo.findById(userDto.getCountryId())
			    .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + userDto.getCountryId()));

			StateEntity state = stateRepo.findById(userDto.getStateId())
			    .orElseThrow(() -> new IllegalArgumentException("State not found with ID: " + userDto.getStateId()));

			CityEntity city = cityRepo.findById(userDto.getCityId())
			    .orElseThrow(() -> new IllegalArgumentException("City not found with ID: " + userDto.getCityId()));
		
		user.setCityEntity(city);
		user.setCountryEntity(country);
		user.setStateEntity(state);
		UserEntity savedUser=userRepo.save(user);
		//email sending logic we write here
		if(savedUser.getUserId()!=null) {
		String subject="Your MiniProject2 account password";
		String body="Hello "+savedUser.getName()+",<br/>Your account has been created successfully.<br/>"
				+ "Your auto-generated password is: <b>"+savedUser.getPassword()+"</b><br/>"
				+ "Please log in and change it as soon as possible.";
		return  emailService.sendEmail(subject, body, userDto.getEmail());
		
		}
		return false;
	}

	@Override
	public UserDto login(String email, String password) {
	UserEntity userEntity=userRepo.findByEmailAndPassword(email, password);
	if(userEntity!=null) {
		return mapper.map(userEntity, UserDto.class);
	}
	
	return null;
	}

	@Override
	public boolean restPassword(RestPwdDto restPwdDto) {
		UserEntity userEntity=userRepo.findByEmail(restPwdDto.getEmail());
		if(userEntity!=null) {
		userEntity.setPassword(restPwdDto.getNewPassword());
		userEntity.setUpdatePassword("YES");
		userRepo.save(userEntity);
			return true;
		}return false;
	}

	@Override
	public QuoteApiResponseDto getQuote() {
		/*  String apiUrl = "https://zenquotes.io/api/random";
		RestTemplate rt=new RestTemplate();
		ResponseEntity<QuoteApiResponseDto> forEntity = rt.getForEntity(apiUrl,QuoteApiResponseDto.class);
		return forEntity.getBody();*/
		String apiUrl = "https://zenquotes.io/api/random";
	    RestTemplate rt = new RestTemplate();
	    ResponseEntity<QuoteApiResponseDto[]> response = rt.getForEntity(apiUrl, QuoteApiResponseDto[].class);
	    QuoteApiResponseDto quote = response.getBody()[0];

	    // Translate to Marathi
	    String translatedQuote = translationService.translateToMarathi(quote.getQuote());
	    String translatedAuthor = translationService.translateToMarathi(quote.getAuthor());

	    return new QuoteApiResponseDto(quote.getId(), translatedQuote, translatedAuthor);
	}
	
	private String generateRandomPassword(int pwdLength) {
		Random random=new Random();
		String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789@#%^&*(^)!";
		
		StringBuffer buffer=new StringBuffer(pwdLength);
		for(int i=0;i<pwdLength;i++) {
			int randomIndex=random.nextInt(chars.length());
			char ch=chars.charAt(randomIndex);
			buffer.append(ch);
		}
		return buffer.toString();
	}
	
	
	
}
