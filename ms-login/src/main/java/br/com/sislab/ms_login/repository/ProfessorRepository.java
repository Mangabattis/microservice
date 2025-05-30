package br.com.sislab.ms_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sislab.ms_login.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    boolean existsByEmail(String email);
    Optional<Professor> findByEmailAndSenha(String email, String senha);
    Optional<Professor> findByEmail(String email);
}
