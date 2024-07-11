package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private  CustomUserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/assets/**", "/public/**").permitAll()
                        .requestMatchers(
                                "/nhanvien/create",
                                "/nhanvien/{maNV}/update",
                                "/nhanvien/{maNV}/delete",
                                "/duan/create",
                                "/duan/{maDA}/update",
                                "/duan/{maDA}/delete",
                                "/duan/{maDA}/addDANV",
                                "/duan/{maDA}/delete/{maNV}",
                                "/luong/**",
                                "/user/**",
                                "/phongban/create",
                                "/phongban/{maPB}/update",
                                "/phongban/{maPB}/update",
                                "/role/create",
                                "/role/{id}/update",
                                "/role/{id}/delete",
                                "/chucvu/create",
                                "/chucvu/{maChucVu}/update",
                                "/chucvu/{maChucVu}/delete"

                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "/nhanvien/view",
                                "/chucvu/view"
                        ).hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/nhanvien/view", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
