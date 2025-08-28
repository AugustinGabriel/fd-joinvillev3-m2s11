package br.futurodev.jmt.m2s10.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SegurancaConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // Exemplos com endpoints
                        .requestMatchers("/login", "/inicio").permitAll()
                        .requestMatchers("/inicio/protected/user").hasAnyRole("USUARIO", "ADMIN")
                        .requestMatchers("/inicio/**").hasRole("ADMIN")

                        // Exemplos com métodos e endpoints
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios").hasRole("ADMIN")

                        // Qualquer outra requisição precisa estar autenticado
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        String password = encoder.encode("123");

        UserDetails usuario = User.withUsername("user")
                .password(password)
                .roles("USUARIO")
                .build();

        UserDetails admin = User.withUsername("adm")
                .password(password)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario, admin);
    }

}
