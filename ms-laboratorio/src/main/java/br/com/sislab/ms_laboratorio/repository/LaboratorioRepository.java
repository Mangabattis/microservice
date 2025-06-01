package br.com.sislab.ms_laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sislab.ms_laboratorio.entities.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>{

    @Query("SELECT COUNT(l) FROM Laboratorio l WHERE l.available = true")
    Long total_lab_disponivel();
    
}