package br.pucminas.aluguel.controllers;

import br.pucminas.aluguel.dto.aluguel.AluguelDTO;
import br.pucminas.aluguel.dto.aluguel.AtualizarAluguelDTO;
import br.pucminas.aluguel.dto.aluguel.CriarAluguelDTO;
import br.pucminas.aluguel.models.Aluguel;
import br.pucminas.aluguel.models.Automovel;
import br.pucminas.aluguel.repositories.AluguelRepository;
import br.pucminas.aluguel.repositories.AutomovelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public AluguelDTO criarAluguel(@RequestBody CriarAluguelDTO criarAluguelDTO) {

        if (aluguelRepository.existsByDateRange(criarAluguelDTO.getInicio(), criarAluguelDTO.getTermino())) {
            throw new IllegalArgumentException("O automóvel não está disponível para o período informado.");
        }

        Automovel automovel = automovelRepository.findById(criarAluguelDTO.getAutomovelId())
                .orElseThrow(() -> new IllegalArgumentException("O automóvel informado não existe."));

        Aluguel aluguel = new Aluguel(criarAluguelDTO.getInicio(), criarAluguelDTO.getTermino(), automovel);

        aluguel = aluguelRepository.save(aluguel);
        return objectMapper.convertValue(aluguel, AluguelDTO.class);

    }

    @GetMapping
    public List<AluguelDTO> listarTodos() {
        List<Aluguel> alugueis = aluguelRepository.findAll();
        return alugueis.stream()
                .map(aluguel -> objectMapper.convertValue(aluguel, AluguelDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<AluguelDTO> buscarAluguelPorId(@PathVariable Long id) {
        return aluguelRepository.findById(id)
                .map(aluguel -> objectMapper.convertValue(aluguel, AluguelDTO.class));
    }

    @PutMapping("/{id}")
    public AluguelDTO atualizarAluguel(@PathVariable Long id, @RequestBody AtualizarAluguelDTO atualizarAluguelDTO) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow();

        aluguel.setStatus(atualizarAluguelDTO.getStatus());

        Aluguel updatedAluguel = aluguelRepository.save(aluguel);
        return objectMapper.convertValue(updatedAluguel, AluguelDTO.class);
    }

    @DeleteMapping("/{id}")
    public String deletarAluguel(@PathVariable Long id) {
        aluguelRepository.deleteById(id);
        return "Aluguel deletado com sucesso!";
    }
}