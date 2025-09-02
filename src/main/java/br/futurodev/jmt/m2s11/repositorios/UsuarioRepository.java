package br.futurodev.jmt.m2s11.repositorios;

import br.futurodev.jmt.m2s11.entidades.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

}
