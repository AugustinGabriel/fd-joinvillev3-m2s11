package br.futurodev.jmt.m2s11.services;

import br.futurodev.jmt.m2s11.configs.JwtConfig;
import br.futurodev.jmt.m2s11.dtos.LoginRequisicaoDto;
import br.futurodev.jmt.m2s11.dtos.LoginRespostaDto;
import br.futurodev.jmt.m2s11.entidades.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginRespostaDto autenticar(LoginRequisicaoDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(), dto.senha()
                )
        );

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("E-mail ou senha inválidos!");
        }

        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        String token = jwtConfig.generateToken(usuario);

        return LoginRespostaDto.builder()
                .tipo("Bearer")
                .token(token)
                .build();
    }

}
