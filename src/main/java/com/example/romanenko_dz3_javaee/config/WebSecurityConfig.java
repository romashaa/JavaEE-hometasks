package com.example.romanenko_dz3_javaee.config;

import com.example.romanenko_dz3_javaee.repository.UserRepository;
import com.example.romanenko_dz3_javaee.service.MyUserDetailsService;
import com.example.romanenko_dz3_javaee.type.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RequiredArgsConstructor
    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserRepository userRepository;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/book-create").hasAuthority(Permission.ADMIN.name())
                    .antMatchers("/favourites", "/favourites/**", "/get-favourites").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin().permitAll()
                    .and()
                    .logout().permitAll();
        }

        @Bean
        @Override
        protected UserDetailsService userDetailsService() {
            return new MyUserDetailsService(userRepository);
        }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    }


