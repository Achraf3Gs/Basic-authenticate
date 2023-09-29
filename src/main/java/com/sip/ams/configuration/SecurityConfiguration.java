package com.sip.ams.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.beans.Customizer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
		manager.setDataSource(dataSource);
		manager.setUsersByUsernameQuery(usersQuery);
		manager.setAuthoritiesByUsernameQuery(rolesQuery);
		return manager;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authz) -> authz
				.requestMatchers("/role/*").permitAll()
				.requestMatchers("/registration").permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()).httpBasic();
		return http.build();
	}



}
