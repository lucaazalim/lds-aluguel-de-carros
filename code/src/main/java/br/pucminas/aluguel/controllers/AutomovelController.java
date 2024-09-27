package br.pucminas.aluguel.controllers;

import br.pucminas.aluguel.dto.automovel.AtualizarAutomovelDTO;
import br.pucminas.aluguel.dto.automovel.AutomovelDTO;
import br.pucminas.aluguel.dto.automovel.CriarAutomovelDTO;
import br.pucminas.aluguel.models.Automovel;
import br.pucminas.aluguel.repositories.AutomovelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public AutomovelDTO criarAutomovel(@RequestBody CriarAutomovelDTO criarAutomovelDTO) {
        Automovel automovel = objectMapper.convertValue(criarAutomovelDTO, Automovel.class);
        automovel = automovelRepository.save(automovel);
        return objectMapper.convertValue(automovel, AutomovelDTO.class);
    }

    @GetMapping
    public List<AutomovelDTO> listarTodos() {
        List<Automovel> automoveis = automovelRepository.findAll();
        return automoveis.stream()
                .map(automovel -> objectMapper.convertValue(automovel, AutomovelDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<AutomovelDTO> buscarAutomovelPorId(@PathVariable Long id) {
        return automovelRepository.findById(id)
                .map(automovel -> objectMapper.convertValue(automovel, AutomovelDTO.class));
    }

    @PutMapping("/{id}")
    public AutomovelDTO atualizarAutomovel(@PathVariable Long id, @RequestBody AtualizarAutomovelDTO atualizarAutomovelDTO) {
        Automovel automovel = automovelRepository.findById(id).orElseThrow();

        automovel.setValorDiaria(atualizarAutomovelDTO.getValorDiaria());

        Automovel updatedAutomovel = automovelRepository.save(automovel);
        return objectMapper.convertValue(updatedAutomovel, AutomovelDTO.class);
    }

    @DeleteMapping("/{id}")
    public String deletarAutomovel(@PathVariable Long id) {
        automovelRepository.deleteById(id);
        return "Automóvel deletado com sucesso!";
    }

}