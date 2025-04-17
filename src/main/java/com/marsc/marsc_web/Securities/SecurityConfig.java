package com.marsc.marsc_web.Securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/", "/home", "/login", "/register").permitAll()  // Public pages
             .requestMatchers("/Marsc/dashboard","/Marsc/contact").permitAll()  // Changed from /Marsc/dashboard
             .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**","/favicons/**", "/webjars/**").permitAll()
             .anyRequest().authenticated() 
             )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .defaultSuccessUrl("/dashboard")
//                .failureUrl("/login?error=true")
//            )
//            .logout(logout -> logout
//                .logoutSuccessUrl("/login?logout")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//            )
//            .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .invalidSessionUrl("/login?invalid")
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false)
//                .expiredUrl("/login?expired")
//            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

 
}