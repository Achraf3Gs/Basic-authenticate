package com.sip.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JenkinsDeployApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(JenkinsDeployApplication.class, args);
	}

}
