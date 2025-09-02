package br.futurodev.jmt.m2s11.dtos;

import br.futurodev.jmt.m2s11.enums.Perfil;

public record UsuarioRequisicaoDto(
        String nome,
        String email,
        String senha,
        Perfil perfil
) {
}
