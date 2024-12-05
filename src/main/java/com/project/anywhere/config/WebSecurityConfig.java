package com.project.anywhere.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.filter.JwtAuthenticationFilter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

                httpSecurity
                                .cors(cors -> cors
                                                .configurationSource(corsConfigurationSource()))
                                .csrf(CsrfConfigurer::disable)
                                .httpBasic(HttpBasicConfigurer::disable)
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(request -> request
                                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/**").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/**").permitAll()

                                                
                                                // .requestMatchers(HttpMethod.GET, "/api/v1/auth/**").permitAll()
                                                // .requestMatchers(HttpMethod.POST,"/api/v1/auth/id-check", "/api/v1/auth/tel-auth","/api/v1/auth/tel-auth-check","/api/v1/auth/sign-up","/api/v1/auth/sign-in","/api/v1/auth/send-auth","/api/v1/auth/find-id","/api/v1/auth/password-send-auth","/api/v1/auth/find-password").permitAll()
                                                // .requestMatchers(HttpMethod.GET, "/api/v1/review/**").permitAll()
                                                // .requestMatchers(HttpMethod.GET, "/api/v1/recommend/**").permitAll()
                                                // .requestMatchers(HttpMethod.GET, "/api/v1/mypage/**").permitAll()
                                                // .requestMatchers(HttpMethod.GET, "/file/**").permitAll()
                                                
                                                // // reviewController
                                                // .requestMatchers(HttpMethod.POST, "/api/v1/review","/api/v1/review/","/api/v1/review/*/comments","/api/v1/review/*/comments/*",
                                                //                 "/api/v1/review/like/*",
                                                //                 "/api/v1/review/scrap/*")
                                                //                 .authenticated()
                                                // .requestMatchers(HttpMethod.PATCH, "/api/v1/review/*")
                                                //                 .authenticated()
                                                // .requestMatchers(HttpMethod.DELETE, "/api/v1/review/*",
                                                //                 "/api/v1/review/*/comments/*")
                                                //                 .authenticated()
                                                

                                                // // recommendController
                                                // .requestMatchers(HttpMethod.POST, "/api/v1/recommend/*",
                                                //                 "/api/v1/recommend/*/comments",
                                                //                 "/api/v1/recommend/like/*")
                                                //                 .authenticated()
                                                // .requestMatchers(HttpMethod.PATCH,"/api/v1/recommend/*",
                                                //                 "/api/v1/recommend/*/comments/*")
                                                //                 .authenticated()
                                                // .requestMatchers(HttpMethod.DELETE,"/api/v1/recommend/*",
                                                //                 "/api/v1/recommend/*/comments/*")
                                                //                 .authenticated()
                                                        
                                                // // userController
                                                // .requestMatchers(HttpMethod.PATCH, "/api/v1/mypage",
                                                //                 "/api/v1/mypage/tel-auth",
                                                //                 "/api/v1/mypage/tel-auth-check", "/api/v1/mypage/comment",
                                                //                 "/api/v1/mypage/update-password").authenticated()


                                                // adminController
                                                .requestMatchers("/api/v1/admin").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
                                
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build();

        }

        @Bean
        protected CorsConfigurationSource corsConfigurationSource() {

                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedMethod("*");
                configuration.addAllowedOrigin("*");
                configuration.addAllowedHeader("*");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }

        class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {
                @Override
                public void commence(HttpServletRequest request, HttpServletResponse response,
                                AuthenticationException authException) throws IOException, ServletException {
                        authException.printStackTrace();
                        response.setContentType("application/json");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.getWriter()
                                        .write("{ \"code\": \"" + ResponseCode.AUTHENTICATION_FAIL
                                                        + "\", \"message\": \""
                                                        + ResponseMessage.AUTHENTICATION_FAIL + "\" }");

                }
        }
}