package br.futurodev.jmt.m2s10.controladores;

import br.futurodev.jmt.m2s10.dtos.UsuarioRequisicaoDto;
import br.futurodev.jmt.m2s10.dtos.UsuarioRespostaDto;
import br.futurodev.jmt.m2s10.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioRespostaDto> get() {
        return service.buscarTodos();
    }

    @GetMapping("{id}")
    public UsuarioRespostaDto getById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRespostaDto post(@RequestBody UsuarioRequisicaoDto dto) {
        return service.criar(dto);
    }

    @PutMapping("{id}")
    public UsuarioRespostaDto put(@PathVariable Long id, @RequestBody UsuarioRequisicaoDto dto) {
        return service.alterar(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.excluir(id);
    }

}
