package com.almundo.callcenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author axel.flores
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebFlux
@TestPropertySource(locations = "classpath:application.yml")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = CallCenterApplication.class)
public class CallCenterApplicationTests {

	@Test
	public void contextLoads() {
		
	}
}