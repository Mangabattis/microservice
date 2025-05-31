package com.ucsal.pimbas.mssoftware.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.pimbas.mssoftware.entities.Software;
import com.ucsal.pimbas.mssoftware.services.SoftwareService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/software")
@CrossOrigin("*")
@Tag(name = "Software", description = "API para gerenciamento de softwares")
public class SoftwareController {

    private final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService){
        this.softwareService = softwareService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os softwares", description = "Retorna uma lista com todos os softwares cadastrados")
    public ResponseEntity<List<Software>> listarSoftwares(){
        return ResponseEntity.ok(softwareService.listarSoftwares());
    }
    
    @GetMapping("/disponiveis")
    @Operation(summary = "Listar softwares disponíveis", description = "Retorna apenas os softwares disponíveis para instalação")
    public ResponseEntity<List<Software>> listarSoftwaresDisponiveis(){
        return ResponseEntity.ok(softwareService.listarSoftwaresDisponiveis());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar software por ID", description = "Retorna um software específico pelo seu ID")
    public ResponseEntity<?> buscarPorId(@PathVariable @Parameter(description = "ID do software") Long id) {
        Optional<Software> software = softwareService.buscarPorId(id);
        if (software.isPresent()) {
            return ResponseEntity.ok(software.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Software não encontrado"));
    }
    
    @GetMapping("/buscar")
    @Operation(summary = "Buscar software por nome", description = "Busca softwares que contenham o nome especificado")
    public ResponseEntity<List<Software>> buscarPorNome(@RequestParam @Parameter(description = "Nome para busca") String nome) {
        return ResponseEntity.ok(softwareService.buscarPorNome(nome));
    }
    
    @GetMapping("/livres")
    @Operation(summary = "Listar softwares livres", description = "Retorna apenas os softwares livres (gratuitos)")
    public ResponseEntity<List<Software>> listarSoftwaresLivres(){
        return ResponseEntity.ok(softwareService.listarSoftwaresLivres());
    }
    
    @GetMapping("/proprietarios")
    @Operation(summary = "Listar softwares proprietários", description = "Retorna apenas os softwares proprietários (pagos)")
    public ResponseEntity<List<Software>> listarSoftwaresProprietarios(){
        return ResponseEntity.ok(softwareService.listarSoftwaresProprietarios());
    }
    
    @GetMapping("/disponibilidade/{id}")
    @Operation(summary = "Verificar disponibilidade", description = "Verifica se um software está disponível para instalação")
    public ResponseEntity<Map<String, Boolean>> verificarDisponibilidade(@PathVariable Long id) {
        boolean disponivel = softwareService.verificarDisponibilidade(id);
        return ResponseEntity.ok(Map.of("disponivel", disponivel));
    }
    
    @PostMapping("/criar")
    @Operation(summary = "Criar novo software", description = "Cadastra um novo software no sistema")
    public ResponseEntity<?> criarSoftware(@RequestBody Software software) {
        try {
            Software novoSoftware = softwareService.criarSoftware(software);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoSoftware);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao criar software: " + e.getMessage()));
        }
    }
    
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar software", description = "Atualiza as informações de um software existente")
    public ResponseEntity<?> atualizarSoftware(@PathVariable Long id, @RequestBody Software software) {
        try {
            software.setId(id);
            Software softwareAtualizado = softwareService.atualizarSoftware(software);
            return ResponseEntity.ok(softwareAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao atualizar software: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir software", description = "Remove um software do sistema")
    public ResponseEntity<?> excluirSoftware(@PathVariable Long id) {
        try {
            softwareService.excluirSoftware(id);
            return ResponseEntity.ok(Map.of("message", "Software excluído com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao excluir software: " + e.getMessage()));
        }
    }
    
    @PutMapping("/disponibilidade/{id}")
    @Operation(summary = "Alterar disponibilidade", description = "Alterna o status de disponibilidade de um software")
    public ResponseEntity<?> alternarDisponibilidade(@PathVariable Long id) {
        try {
            Software software = softwareService.alternarDisponibilidade(id);
            return ResponseEntity.ok(software);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao alterar disponibilidade: " + e.getMessage()));
        }
    }
}