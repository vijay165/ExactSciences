
//package com.search.api.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		/* super.configure(auth); */
//		auth.inMemoryAuthentication()
//		.withUser("Puser01")
//		.password("Mdmuser01")
//		.roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("Puser01").password("Mdmuser01").roles("USER");
//	}
//	 @Override 
//	 protected void configure(HttpSecurity http) throws Exception {
//		  http.csrf().disable();
//		  http.authorizeRequests().anyRequest().fullyAuthenticated().and().
//		  httpBasic(); }
	 
	 

		/*
		 * @Bean public PasswordEncoder passwordEncoder() { return new
		 * BCryptPasswordEncoder(); }
		 */
//		@Bean
//		public static NoOpPasswordEncoder passwordEncoder() {
//			return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//		}
//
//}
