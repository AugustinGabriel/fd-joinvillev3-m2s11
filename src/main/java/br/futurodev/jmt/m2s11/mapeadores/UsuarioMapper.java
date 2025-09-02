package br.futurodev.jmt.m2s11.mapeadores;

import br.futurodev.jmt.m2s11.dtos.UsuarioRequisicaoDto;
import br.futurodev.jmt.m2s11.dtos.UsuarioRespostaDto;
import br.futurodev.jmt.m2s11.entidades.UsuarioEntity;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioRespostaDto toDto(UsuarioEntity entity) {
        return UsuarioRespostaDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .perfil(entity.getPerfil())
                .build();
    }

    public static void toEntity(UsuarioEntity entity, UsuarioRequisicaoDto dto) {
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setPerfil(dto.perfil());
    }
}
