package com.practice.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.HungerBoxUser;
@Repository
public interface UserRepository extends JpaRepository<HungerBoxUser, Long>{
	
}
