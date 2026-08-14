package com.miniProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject2.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{

public 	boolean existsByEmail(String email);
public UserEntity findByEmailAndPassword(String email,String password);
public UserEntity findByEmail(String email);

}
