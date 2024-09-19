package br.pucminas.aluguel.controller;

import br.pucminas.aluguel.dto.AtualizarClienteDTO;
import br.pucminas.aluguel.dto.ClienteDTO;
import br.pucminas.aluguel.dto.CriarClienteDTO;
import br.pucminas.aluguel.model.Cliente;
import br.pucminas.aluguel.repository.ClienteRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ClienteDTO criarCliente(@RequestBody CriarClienteDTO criarClienteDTO) {
        Cliente cliente = objectMapper.convertValue(criarClienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        return objectMapper.convertValue(cliente, ClienteDTO.class);
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class));
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDTO atualizarClienteDTO) throws JsonMappingException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        objectMapper.updateValue(atualizarClienteDTO, cliente);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "Cliente deletado com sucesso!";
    }

}
