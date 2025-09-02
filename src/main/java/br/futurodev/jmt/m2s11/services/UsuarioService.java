package br.futurodev.jmt.m2s11.services;

import br.futurodev.jmt.m2s11.dtos.UsuarioRequisicaoDto;
import br.futurodev.jmt.m2s11.dtos.UsuarioRespostaDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {

    List<UsuarioRespostaDto> buscarTodos();

    UsuarioRespostaDto buscarPorId(Long id);

    UsuarioRespostaDto criar(UsuarioRequisicaoDto dto);

    UsuarioRespostaDto alterar(Long id, UsuarioRequisicaoDto dto);

    void excluir(Long id);

}
