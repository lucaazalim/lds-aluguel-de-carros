package br.pucminas.aluguel.controllers;

import br.pucminas.aluguel.dto.automovel.AtualizarAutomovelDTO;
import br.pucminas.aluguel.dto.automovel.AutomovelDTO;
import br.pucminas.aluguel.dto.automovel.CriarAutomovelDTO;
import br.pucminas.aluguel.dto.cliente.ClienteDTO;
import br.pucminas.aluguel.models.Automovel;
import br.pucminas.aluguel.models.Cliente;
import br.pucminas.aluguel.repositories.AutomovelRepository;
import br.pucminas.aluguel.repositories.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public AutomovelDTO criarAutomovel(@RequestBody CriarAutomovelDTO criarAutomovelDTO) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Automovel automovel = objectMapper.convertValue(criarAutomovelDTO, Automovel.class);

        Cliente clienteAutenticado = clienteRepository.findByEmail(email).orElseThrow();

        automovel.setProprietario(clienteAutenticado);
        automovel = automovelRepository.save(automovel);

        return toDTO(automovel);

    }

    @GetMapping
    public List<AutomovelDTO> listarTodos() {
        List<Automovel> automoveis = automovelRepository.findAll();
        return automoveis.stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<AutomovelDTO> buscarAutomovelPorId(@PathVariable Long id) {
        return automovelRepository.findById(id)
                .map(this::toDTO);
    }

    @PutMapping("/{id}")
    public AutomovelDTO atualizarAutomovel(@PathVariable Long id, @RequestBody AtualizarAutomovelDTO atualizarAutomovelDTO) {
        Automovel automovel = automovelRepository.findById(id).orElseThrow();

        automovel.setValorDiaria(atualizarAutomovelDTO.getValorDiaria());

        Automovel updatedAutomovel = automovelRepository.save(automovel);
        return toDTO(updatedAutomovel);
    }

    @DeleteMapping("/{id}")
    public String deletarAutomovel(@PathVariable Long id) {
        automovelRepository.deleteById(id);
        return "Automóvel deletado com sucesso!";
    }

    private AutomovelDTO toDTO(Automovel automovel) {
        AutomovelDTO automovelDTO = objectMapper.convertValue(automovel, AutomovelDTO.class);
        ClienteDTO clienteDTO = objectMapper.convertValue(automovel.getProprietario(), ClienteDTO.class);
        automovelDTO.setProprietario(clienteDTO);
        return automovelDTO;
    }

}