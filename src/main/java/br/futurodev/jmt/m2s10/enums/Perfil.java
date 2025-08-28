package br.futurodev.jmt.m2s10.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Perfil {

    USUARIO("Usuário", "Usuário comum"),
    ADMIN("Administrador", "Administrador do sistema");

    private final String nome;
    private final String descricao;

}
