package br.com.sislab.ms_login.repository.interfaces;

import java.util.Map;

import br.com.sislab.ms_login.entities.Administrador;
import br.com.sislab.ms_login.entities.Professor;
import br.com.sislab.ms_login.entities.dtos.UsuarioDTO;

public interface IUsuario {
    Professor registrarProfessor(UsuarioDTO user);
    Administrador registrarAdministrador(UsuarioDTO user);
    boolean UsuarioExiste(String email, String senha);
    Map<String, Object> obterDadosUsuario(String email);
}
