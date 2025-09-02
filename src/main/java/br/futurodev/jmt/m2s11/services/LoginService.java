package br.futurodev.jmt.m2s11.services;

import br.futurodev.jmt.m2s11.dtos.LoginRequisicaoDto;
import br.futurodev.jmt.m2s11.dtos.LoginRespostaDto;

public interface LoginService {

    LoginRespostaDto autenticar(LoginRequisicaoDto dto);

}
