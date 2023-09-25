package com.example.foodapp.config;

import com.example.foodapp.auth.config.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean(name = "mvcHandlerMappingIntrospector2")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        MvcRequestMatcher.Builder builder = new MvcRequestMatcher.Builder(new HandlerMappingIntrospector());
//
        http
//                .oauth2Client()
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(builder.pattern("/api/v1/**")).permitAll()
//                        .requestMatchers(builder.pattern("/api/v1/user/**")).permitAll()
//                        .requestMatchers(builder.pattern("/swagger-ui")).permitAll()
//                        .requestMatchers(builder.pattern("/swagger-resources/**")).permitAll()
//                        .requestMatchers(builder.pattern("/configuration/security")).permitAll()
//                        .requestMatchers(builder.pattern("/swagger-ui.html")).permitAll()
//                        .requestMatchers(builder.pattern("/swagger-ui/*")).permitAll()
//                        .requestMatchers(builder.pattern("/webjars/**")).permitAll()
//                        .requestMatchers(builder.pattern("/v2/**")).permitAll()
                                .anyRequest().permitAll()
//                        .anyRequest().authenticated()

                )

                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe(Customizer.withDefaults())

                .csrf(AbstractHttpConfigurer::disable);
//                .cors(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3001"));
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
//        configuration.setAllowedHeaders(List.of("Access-Control-Allow-Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedOrigins("http://localhost:8070", "http://localhost:3001")
                        .allowedMethods("*");
            }
        };
    }
}
