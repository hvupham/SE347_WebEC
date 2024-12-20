package com.project.shopapp.configurations;

import com.project.shopapp.filters.JwtTokenFilter;
<<<<<<< HEAD
import com.project.shopapp.repositories.EmailRepository;
import com.project.shopapp.services.Email.EmailService;
import com.project.shopapp.services.Facebook.FacebookService;
=======
>>>>>>> c5c6824916064946fedd6ae8d711a052fc9c0cdc
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
<<<<<<< HEAD
    private final EmailService emailService;
    private final FacebookService facebookService;
    private final EmailRepository emailRepository;

=======
>>>>>>> c5c6824916064946fedd6ae8d711a052fc9c0cdc

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> {
                    requests
<<<<<<< HEAD
                            .anyRequest().permitAll(); // Bạn có thể thay đổi cấu hình này để yêu cầu xác thực cho các endpoint cụ thể
                })
                .oauth2Login(withDefaults())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class); // Thêm filter JWT
=======
                            .anyRequest().permitAll();
                })
                .oauth2Login(withDefaults())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
>>>>>>> c5c6824916064946fedd6ae8d711a052fc9c0cdc

        http.cors(Customizer.withDefaults());

//        http.oauth2Login(oauth2 -> oauth2.successHandler(authenticationSuccessHandler()));
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Origin", "Authorization", "content-type", "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });

        return http.build();
    }
<<<<<<< HEAD
=======

>>>>>>> c5c6824916064946fedd6ae8d711a052fc9c0cdc
}
