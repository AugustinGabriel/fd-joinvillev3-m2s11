package br.futurodev.jmt.m2s10.services;

import br.futurodev.jmt.m2s10.dtos.LoginRequisicaoDto;
import br.futurodev.jmt.m2s10.dtos.LoginRespostaDto;

public interface LoginService {

    LoginRespostaDto autenticar(LoginRequisicaoDto dto);

}
