package com.HFTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	@Autowired
//	private UserDetailsService userDetailsService;
	
	
//	 @Bean
//	  public FilterRegistrationBean corsFilter() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("http://localhost:8080"); // @Value: http://localhost:8080
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("*");
//	    source.registerCorsConfiguration("/**", config);
//	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//	    bean.setOrder(0);
//	    return bean;
//	  }

	
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
					.authorizeRequests().antMatchers("/shops").permitAll().and()
					.authorizeRequests().antMatchers("/login").permitAll();
		
//		httpSecurity.authorizeRequests().antMatchers("/").permitAll()
//					.anyRequest().authenticated().and()
//					.formLogin().loginPage("/login").permitAll().and()
//					.logout().permitAll();
		
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
	}
	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(userDetailsService);
		
//		auth.inMemoryAuthentication()
//			.withUser("yushiki").password("password").roles("USER")
//			.and()
//			.withUser("ayoub").password("password").roles("USER", "ADMIN");
//	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
		
	}
	
}
