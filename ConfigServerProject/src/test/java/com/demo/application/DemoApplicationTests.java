package com.demo.application;

import org.junit.jupiter.api.Test;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
