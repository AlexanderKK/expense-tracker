package me.alexander.expensetracker.config;

import me.alexander.expensetracker.config.csrf.CsrfRequestMatcher;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf
                        .requireCsrfProtectionMatcher(matcher -> new CsrfRequestMatcher().buildMatcher(matcher))
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                ).headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .xssProtection(
                                xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
                        ).contentSecurityPolicy(
                                cps -> cps.policyDirectives("script-src 'self'")
                        )
                ).authorizeHttpRequests(requests -> requests
                        // Anonymous
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/sounds/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/users/register").permitAll()

                        // Users
                        .requestMatchers("/categories/**").hasRole("USER")
                        .requestMatchers("/transactions/**").hasRole("USER")
                        .requestMatchers("/incomes/**").hasRole("USER")
                        .requestMatchers("/balance/**").hasRole("USER")
                        .requestMatchers("/home/**").hasRole("USER")
                ).build();
    }

    @Bean
    public PasswordEncoder initPasswordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
