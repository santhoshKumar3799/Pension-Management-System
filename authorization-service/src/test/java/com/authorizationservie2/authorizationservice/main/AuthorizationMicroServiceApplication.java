package com.authorizationservie2.authorizationservice.main;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorizationservie2.authorizationservice.AuthorizationServiceApplication;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthorizationMicroServiceApplication {


	@Test
	public void main() {
		AuthorizationServiceApplication.main(new String[] {});
	}
}
