package com.liang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zl
 */
//@ImportResource(locations = "config/applicationContext.xml")
@SpringBootApplication(scanBasePackages = {"com.liang"})
public class TestApplication {

	public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
	}

}