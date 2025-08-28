package br.futurodev.jmt.m2s10.dtos;

import br.futurodev.jmt.m2s10.enums.Perfil;

public record UsuarioRequisicaoDto(
        String nome,
        String email,
        String senha,
        Perfil perfil
) {
}
