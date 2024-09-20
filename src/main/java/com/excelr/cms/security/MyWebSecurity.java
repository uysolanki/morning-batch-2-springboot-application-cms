package com.excelr.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter
{

//Authentication code
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	 auth.inMemoryAuthentication()
//		.withUser("jetha")
//		.password("babita")		// cleartext
//		.authorities("ADMIN")
//		.and()
//		.withUser("tappu")
//		.password("sonu")		// cleartext
//		.authorities("ADMIN")
//	 	.and()
//		.withUser("bagha")
//		.password("bawri")		// cleartext
//		.authorities("USER")
//	 	.and()
//		.withUser("nattu")
//		.password("jaywanti")		// cleartext
//		.authorities("USER")
//	 	.and()
//		.withUser("magan")
//		.password("gadael")		// cleartext
//		.authorities("USER");
		auth.authenticationProvider(myAuthenticationProvider());
}

@Bean
public AuthenticationProvider myAuthenticationProvider() {
	DaoAuthenticationProvider myDao=new DaoAuthenticationProvider();
	myDao.setUserDetailsService(mySetUserDetailsService());
	myDao.setPasswordEncoder(mySetPasswordEncoder());
	return myDao;
}

@Bean
public PasswordEncoder mySetPasswordEncoder() {
	return new BCryptPasswordEncoder();
}

@Bean
public UserDetailsService mySetUserDetailsService() {
	return new MyUserDetailsService();
}

//Authorisation code
	@Override
	protected void configure(HttpSecurity http) throws Exception 
    {
        http.authorizeRequests()
        .antMatchers("/","/register","/403").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/updatecustomerform/**","/deletecustomer/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        .and()
        .cors().and().csrf().disable();
	} 

//	@Bean
//	public PasswordEncoder getPasswordEncoder()
//	{
//		return NoOpPasswordEncoder.getInstance();
//	}

}

