package com.authorizationservie2.authorizationservice.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.authorizationservie2.authorizationservice.AuthorizationServiceApplication;


@SpringBootTest
public class AuthorizationMicroServiceApplication {


	@Test
	public void main() {
		AuthorizationServiceApplication.main(new String[] {});
	}
}
