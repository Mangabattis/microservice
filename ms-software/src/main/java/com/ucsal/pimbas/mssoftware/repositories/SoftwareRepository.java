package com.ucsal.pimbas.mssoftware.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucsal.pimbas.mssoftware.entities.Software;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Long> {
    
    // Buscar apenas softwares disponíveis
    List<Software> findByAvailableTrue();
    
    // Buscar por nome (case insensitive)
    List<Software> findByNameContainingIgnoreCase(String name);
    
    // Buscar softwares livres ou proprietários
    List<Software> findBySoftwareFree(boolean softwareFree);
    
    // Query customizada para buscar softwares disponíveis e livres
    @Query("SELECT s FROM Software s WHERE s.available = true AND s.softwareFree = true")
    List<Software> findAvailableFreeSoftware();
}