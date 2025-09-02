package br.futurodev.jmt.m2s11.controladores;

import br.futurodev.jmt.m2s11.dtos.LoginRequisicaoDto;
import br.futurodev.jmt.m2s11.dtos.LoginRespostaDto;
import br.futurodev.jmt.m2s11.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService service;

    @PostMapping
    public LoginRespostaDto login(@RequestBody LoginRequisicaoDto dto) {
        return service.autenticar(dto);
    }

}
