package com.tierrafertil.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Cifrado obligatorio de contrasenas (BCrypt), nunca texto plano
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Recursos publicos: home, catalogo de propiedades, login, registro, estaticos
                .requestMatchers(
                        "/", "/inicio",
                        "/propiedades", "/propiedades/{id:[0-9]+}",
                        "/login", "/registro",
                        "/css/**", "/js/**", "/uploads/**", "/webjars/**"
                ).permitAll()
                // Rutas exclusivas de administrador
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Rutas exclusivas de agente inmobiliario
                .requestMatchers("/agente/**").hasAnyRole("AGENTE", "ADMIN")
                // Rutas exclusivas de cliente autenticado
                .requestMatchers("/cliente/**").hasAnyRole("CLIENTE", "ADMIN")
                // Cualquier otra ruta requiere estar autenticado
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout=true")
                .permitAll()
            );
        // CSRF queda activo por defecto (recomendado): los formularios Thymeleaf
        // ya incluyen el token automaticamente con th:action.

        return http.build();
    }
}
