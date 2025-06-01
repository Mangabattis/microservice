package br.com.sislab.ms_laboratorio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.sislab.ms_laboratorio.entities.Laboratorio;
import br.com.sislab.ms_laboratorio.repository.LaboratorioRepository;
import br.com.sislab.ms_laboratorio.repository.interfaces.ILaboratorio;

@Repository
public class LaboratorioImpl implements ILaboratorio {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioImpl(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }

    @Override
    public Optional<Laboratorio> buscarPorId(Long id) {
        return laboratorioRepository.findById(id);
    }

    @Override
    public Laboratorio salvar(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public void excluir(Long id) {
        laboratorioRepository.deleteById(id);
    }

    @Override
    public Laboratorio atualizar(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public void alterarDisponibilidade(Long id, boolean disponibilidade) {
        laboratorioRepository.findById(id).ifPresent(lab -> {
            lab.setAvailable(disponibilidade);
            laboratorioRepository.save(lab);
        });
    }

    @Override
    public Long totalLaboratorio() {
        return laboratorioRepository.total_lab_disponivel();
    }
}