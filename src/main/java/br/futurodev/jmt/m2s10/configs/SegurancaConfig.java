package br.futurodev.jmt.m2s10.configs;

import br.futurodev.jmt.m2s10.enums.Perfil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
                        .requestMatchers("/inicio/protected/user").hasAnyAuthority(
                                Perfil.USUARIO.name(),
                                Perfil.ADMIN.name()
                        )
                        .requestMatchers("/inicio/**").hasAuthority(Perfil.ADMIN.name())

                        // Exemplos com métodos e endpoints
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyAuthority(
                                Perfil.USUARIO.name(),
                                Perfil.ADMIN.name()
                        )
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasAuthority(Perfil.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAuthority(Perfil.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority(Perfil.ADMIN.name())

                        // Qualquer outra requisição precisa estar autenticado
                        .anyRequest().denyAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        String password = encoder.encode("123");

        UserDetails usuario = User.withUsername("user")
                .password(password)
                .roles(Perfil.USUARIO.name())
                .build();

        UserDetails admin = User.withUsername("adm")
                .password(password)
                .roles(Perfil.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(usuario, admin);
    }

}
