package com.authorizationservie2.authorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorizationservie2.authorizationservice.model.UserData;

/**
 * This repository interface is been used to access user credentials table
 * @param <UserData>
 */
@Repository
public interface UserRepository extends JpaRepository<UserData, String>{

}
