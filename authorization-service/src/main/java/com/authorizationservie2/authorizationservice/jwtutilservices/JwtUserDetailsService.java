package com.authorizationservie2.authorizationservice.jwtutilservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorizationservie2.authorizationservice.model.UserData;
import com.authorizationservie2.authorizationservice.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * Loads user from the database if it exists. After loading the details,
	 * compares the given username from username in the DB.
	 * 
	 * @param username
	 * @return UserDetails
	 */

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String uid) {
		/** fetching user by userName, if user is null the throw exception, otherwise
		 * return user
		 */
		logger.info("START");
		try {
			UserData custuser = userRepository.findById(uid).orElse(null);
			if (custuser != null) {
				custuser.getUname();

				logger.info("END - User found");

				return new User(custuser.getUserid(), custuser.getUpassword(),
						(Collection<? extends GrantedAuthority>) new ArrayList<Object>());
			} else {

				logger.info("END - UsernameNotFound");

				throw new UsernameNotFoundException("User not found with userID: "  + uid);
			}
		} catch (Exception e) {
			logger.info("EXCEPTION - UsernameNotFoundException");

			throw new UsernameNotFoundException("User not found with userID: "  + uid);
		}

	}

}
