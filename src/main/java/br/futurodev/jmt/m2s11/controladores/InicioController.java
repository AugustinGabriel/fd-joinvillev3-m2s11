package br.futurodev.jmt.m2s11.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inicio")
public class InicioController {

    @GetMapping
    public String get() {
        return "Aplicação funcionando";
    }

    @GetMapping("protected")
    public String getProtected() {
        return "Aplicação funcionando (àrea restrita)";
    }

    @GetMapping("protected/user")
    public String getProtectedUser() {
        return "Aplicação funcionando (àrea restrita - USER)";
    }

    @GetMapping("protected/admin")
    public String getProtectedAdmin() {
        return "Aplicação funcionando (àrea restrita - ADMIN)";
    }

}
