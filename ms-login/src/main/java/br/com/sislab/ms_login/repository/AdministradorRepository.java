package br.com.sislab.ms_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sislab.ms_login.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    boolean existsByEmail(String email);
    Optional<Administrador> findByEmailAndSenha(String email, String senha);
    Optional<Administrador> findByEmail(String email);
}
