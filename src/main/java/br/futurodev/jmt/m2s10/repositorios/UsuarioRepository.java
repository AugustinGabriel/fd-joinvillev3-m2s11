package br.futurodev.jmt.m2s10.repositorios;

import br.futurodev.jmt.m2s10.entidades.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
