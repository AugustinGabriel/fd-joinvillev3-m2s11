package br.futurodev.jmt.m2s11.dtos;

import br.futurodev.jmt.m2s11.enums.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRespostaDto {

    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;

}
