package com.ucsal.pimbas.mssoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucsal.pimbas.mssoftware.entities.Software;
import com.ucsal.pimbas.mssoftware.repositories.SoftwareRepository;

@Service
public class SoftwareService {
    
    private final SoftwareRepository softwareRepository;

    public SoftwareService(SoftwareRepository softwareRepository){
        this.softwareRepository = softwareRepository;
    }

    public List<Software> listarSoftwares(){
        return softwareRepository.findAll();
    }
    
    public List<Software> listarSoftwaresDisponiveis(){
        return softwareRepository.findByAvailableTrue();
    }
    
    public Optional<Software> buscarPorId(Long id) {
        return softwareRepository.findById(id);
    }
    
    public List<Software> buscarPorNome(String name) {
        return softwareRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Software> listarSoftwaresLivres() {
        return softwareRepository.findBySoftwareFree(true);
    }
    
    public List<Software> listarSoftwaresProprietarios() {
        return softwareRepository.findBySoftwareFree(false);
    }
    
    public Software criarSoftware(Software software) {
        // Validações básicas
        if (software.getName() == null || software.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do software é obrigatório");
        }
        if (software.getVersion() == null || software.getVersion().trim().isEmpty()) {
            throw new IllegalArgumentException("Versão do software é obrigatória");
        }
        
        return softwareRepository.save(software);
    }
    
    public Software atualizarSoftware(Software software) {
        if (!softwareRepository.existsById(software.getId())) {
            throw new RuntimeException("Software não encontrado com ID: " + software.getId());
        }
        
        // Validações básicas
        if (software.getName() == null || software.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do software é obrigatório");
        }
        if (software.getVersion() == null || software.getVersion().trim().isEmpty()) {
            throw new IllegalArgumentException("Versão do software é obrigatória");
        }
        
        return softwareRepository.save(software);
    }
    
    public void excluirSoftware(Long id) {
        if (!softwareRepository.existsById(id)) {
            throw new RuntimeException("Software não encontrado com ID: " + id);
        }
        softwareRepository.deleteById(id);
    }
    
    public Software alternarDisponibilidade(Long id) {
        Optional<Software> softwareOpt = softwareRepository.findById(id);
        if (softwareOpt.isEmpty()) {
            throw new RuntimeException("Software não encontrado com ID: " + id);
        }
        
        Software software = softwareOpt.get();
        software.setAvailable(!software.isAvailable());
        return softwareRepository.save(software);
    }
    
    public boolean verificarDisponibilidade(Long id) {
        Optional<Software> software = softwareRepository.findById(id);
        return software.map(Software::isAvailable).orElse(false);
    }
}