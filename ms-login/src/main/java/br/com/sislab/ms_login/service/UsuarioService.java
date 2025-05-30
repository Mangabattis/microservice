package br.com.sislab.ms_login.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.sislab.ms_login.entities.Administrador;
import br.com.sislab.ms_login.entities.Professor;
import br.com.sislab.ms_login.entities.dtos.UsuarioDTO;
import br.com.sislab.ms_login.repository.interfaces.IUsuario;

@Service
public class UsuarioService {
    
    private final IUsuario usuario;

    public UsuarioService(IUsuario usuario){
        this.usuario = usuario;
    }

    public Professor registrarProfessor(UsuarioDTO userDTO) {
        return usuario.registrarProfessor(userDTO);
    }

    public Administrador registrarAdministrador(UsuarioDTO userDTO) {
        return usuario.registrarAdministrador(userDTO);
    }

    public boolean UsuarioExiste(String email, String senha){
        return usuario.UsuarioExiste(email, senha);
    }

    public Map<String, Object> obterDadosUsuario(String email){
        return usuario.obterDadosUsuario(email);
    }

}

