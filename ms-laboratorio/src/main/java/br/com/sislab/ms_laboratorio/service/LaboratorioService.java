package br.com.sislab.ms_laboratorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.sislab.ms_laboratorio.entities.Laboratorio;
import br.com.sislab.ms_laboratorio.repository.interfaces.ILaboratorio;

@Service
public class LaboratorioService {

    private final ILaboratorio laboratorioImpl;

    public LaboratorioService(ILaboratorio laboratorioImpl) {
        this.laboratorioImpl = laboratorioImpl;
    }

    public List<Laboratorio> listarLaboratorios() {
        return laboratorioImpl.listarLaboratorios();
    }

    public Optional<Laboratorio> buscarPorId(Long id) {
        return laboratorioImpl.buscarPorId(id);
    }

    public Laboratorio salvar(Laboratorio laboratorio) {
        return laboratorioImpl.salvar(laboratorio);
    }

    public void excluir(Long id) {
        laboratorioImpl.excluir(id);
    }

    public Laboratorio atualizar(Laboratorio laboratorio) {
        return laboratorioImpl.atualizar(laboratorio);
    }

    public void alterarDisponibilidade(Long id, boolean disponibilidade) {
        laboratorioImpl.alterarDisponibilidade(id, disponibilidade);
    }

    public Long totalLaboratorio() {
        return laboratorioImpl.totalLaboratorio();
    }
}