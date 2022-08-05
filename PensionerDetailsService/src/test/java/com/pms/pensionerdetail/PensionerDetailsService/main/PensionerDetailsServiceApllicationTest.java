package com.pms.pensionerdetail.PensionerDetailsService.main;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.pms.pensionerdetail.PensionerDetailsService.PensionerDetailsServiceApplication;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PensionerDetailsServiceApllicationTest {


	@Test
	public void main() {
		PensionerDetailsServiceApplication.main(new String[] {});
	}

}
