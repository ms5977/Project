package com.blog.mains.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.blog.mains.security.CustomUserDetailService;
import com.blog.mains.security.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecuirtyConfig {
  
	@Autowired
	private CustomUserDetailService customUserDetailService;
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
			"/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
			"/api/test/**", "/authenticate" };
	
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.cors(cors->cors.configurationSource(corsConfigurationSource()))
		.csrf(csr->csr.disable())
		.authorizeHttpRequests((req)->req
		.requestMatchers("/admin/**")
		.hasRole("ADMIN")
		.requestMatchers("/user/**")
		.hasRole("USER")
		.requestMatchers("/authenticate")
		.permitAll()
		.requestMatchers(WHITE_LIST_URL)
		.permitAll()
		.requestMatchers("/v3/api-docs")
		.permitAll()
		.anyRequest()
		.authenticated())
//		.httpBasic(Customizer.withDefaults())
		.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);				
						
						
		return http.build();
	} 
	@Bean
    AuthenticationProvider authenticationProvider()
    {
		System.out.println("1");
    	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	return daoAuthenticationProvider;
    }
	@Bean
	 AuthenticationManager authenticationManager()
	{
		ProviderManager manager=new ProviderManager(authenticationProvider());
		return manager;
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081", "http://localhost:8080")); // Include both Swagger UI and frontend
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
//	    configuration.setAllowCredentials(true); // If you're using credentials
	    configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type")); // Expose headers if needed

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	
//-------------------------------------------------------------------------------------------------------------------------
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails user = User.withUsername("User").password(passwordEncoder().encode("Mani@123")).roles("USER").build();
//		
//		return new  InMemoryUserDetailsManager(user);
//	}
	
//	http.csrf(csr->csr.disable())
//	.authorizeHttpRequests((req)->req.requestMatchers(HttpMethod.GET)
//	.permitAll()
//	.anyRequest()
//	.authenticated())
//	.httpBasic(Customizer.withDefaults())
//	.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

}
