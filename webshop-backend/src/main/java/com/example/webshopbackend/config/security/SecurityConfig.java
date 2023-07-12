package com.example.webshopbackend.config.security;

import com.example.webshopbackend.util.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public NotionConfigurationProperties notionConfigurationProperties() {
        return new NotionConfigurationProperties("P3khZq0v6xCviB59TbOiWd18nJNnvrjaK937eb32GqApl1iDnDnuaqTS8mc");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
                notionConfigurationProperties()
        );
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        return http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests().requestMatchers("/api/login", "/api/refreshtoken", "/api/register").permitAll().and()
                .authorizeHttpRequests().requestMatchers(GET, "/api/users/**").hasAnyAuthority("ROLE_USER").and()
                .authorizeHttpRequests().requestMatchers(POST, "/api/users/save/**").hasAnyAuthority("ROLE_ADMIN").and()
                .authorizeHttpRequests().requestMatchers(POST, "/api/vehicles/save/**").hasAnyAuthority("ROLE_ADMIN").and()
                .authorizeHttpRequests().requestMatchers(DELETE, "/api/vehicles/**").hasAnyAuthority("ROLE_ADMIN").and()
                .authorizeHttpRequests().anyRequest().authenticated().and()
                .addFilter(customAuthenticationFilter)
                .addFilterBefore(
                        new CustomAuthorizationFilter(new ExceptionHandler()),
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
}
