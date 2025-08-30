package br.futurodev.jmt.m2s10.services;

import br.futurodev.jmt.m2s10.dtos.LoginRequisicaoDto;
import br.futurodev.jmt.m2s10.dtos.LoginRespostaDto;
import br.futurodev.jmt.m2s10.entidades.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginRespostaDto autenticar(LoginRequisicaoDto dto) {
        UserDetails usuario = usuarioService.loadUserByUsername(dto.email());

        boolean senhaConfere = passwordEncoder.matches(dto.senha(), usuario.getPassword());
        if (!senhaConfere) {
            throw new RuntimeException("Senha não confere");
        }

        String paraCodificar = usuario.getUsername() + ":" + dto.senha();
        String token = Base64.getEncoder().encodeToString(paraCodificar.getBytes());

        return LoginRespostaDto.builder()
                .tipo("Basic")
                .token(token)
                .build();
    }

}
