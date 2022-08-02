package TestSuite;

import org.junit.runner.RunWith;
import com.authorizationservie2.authorizationservice.controllerTest.AuthControllerTest;
import com.authorizationservie2.authorizationservice.servicesTest.JwtUserDetailsServiceTest;
import com.authorizationservie2.authorizationservice.servicesTest.TokenManagerTest;

import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({AuthControllerTest.class,JwtUserDetailsServiceTest.class,TokenManagerTest.class})
public class JunitTestSuite {

}
