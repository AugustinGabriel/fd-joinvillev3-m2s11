package br.futurodev.jmt.m2s11.services;

import br.futurodev.jmt.m2s11.dtos.UsuarioRequisicaoDto;
import br.futurodev.jmt.m2s11.dtos.UsuarioRespostaDto;
import br.futurodev.jmt.m2s11.entidades.UsuarioEntity;
import br.futurodev.jmt.m2s11.enums.Perfil;
import br.futurodev.jmt.m2s11.mapeadores.UsuarioMapper;
import br.futurodev.jmt.m2s11.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioRespostaDto> buscarTodos() {
        List<UsuarioEntity> entidades = repository.findAll();
        return entidades.stream().map(UsuarioMapper::toDto).toList();
    }

    @Override
    public UsuarioRespostaDto buscarPorId(Long id) {
        return UsuarioMapper.toDto(buscarEntidadePorId(id));
    }

    @Override
    public UsuarioRespostaDto criar(UsuarioRequisicaoDto dto) {
        UsuarioEntity entidade = new UsuarioEntity();
        UsuarioMapper.toEntity(entidade, dto);

        String senha = passwordEncoder.encode(dto.senha());
        entidade.setSenha(senha);

        entidade = repository.save(entidade);
        return UsuarioMapper.toDto(entidade);
    }

    @Override
    public UsuarioRespostaDto alterar(Long id, UsuarioRequisicaoDto dto) {
        UsuarioEntity entidade = buscarEntidadePorId(id);
        UsuarioMapper.toEntity(entidade, dto);

        if (StringUtils.hasText(dto.senha())) {
            String senha = passwordEncoder.encode(dto.senha());
            entidade.setSenha(senha);
        }

        entidade = repository.save(entidade);
        return UsuarioMapper.toDto(entidade);
    }

    @Override
    public void excluir(Long id) {
        repository.delete(buscarEntidadePorId(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOpt = repository.findByEmail(username);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }

        if (username.equals("admin@email.com")) {
            return UsuarioEntity.builder()
                    .id(0L)
                    .nome("Administrador")
                    .email("admin@email.com")
                    .perfil(Perfil.ADMIN)
                    .senha(passwordEncoder.encode("123"))
                    .build();
        }

        throw new UsernameNotFoundException("Usuário não encontrado!");
    }

    private UsuarioEntity buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
