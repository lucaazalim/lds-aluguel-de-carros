package br.pucminas.aluguel.controllers;

import br.pucminas.aluguel.dto.cliente.*;
import br.pucminas.aluguel.models.Cliente;
import br.pucminas.aluguel.repositories.ClienteRepository;
import br.pucminas.aluguel.utils.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ClienteDTO criarCliente(@RequestBody CriarClienteDTO criarClienteDTO) {
        Cliente cliente = objectMapper.convertValue(criarClienteDTO, Cliente.class);
        cliente.setSenha(passwordEncoder.encode(criarClienteDTO.getSenha()));
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
    public ClienteDTO atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDTO atualizarClienteDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();

        cliente.setEmail(atualizarClienteDTO.getEmail());
        cliente.setSenha(passwordEncoder.encode(atualizarClienteDTO.getSenha()));
        cliente.setNome(atualizarClienteDTO.getNome());
        cliente.setEndereco(atualizarClienteDTO.getEndereco());
        cliente.setProfissao(atualizarClienteDTO.getProfissao());

        Cliente updatedCliente = clienteRepository.save(cliente);
        return objectMapper.convertValue(updatedCliente, ClienteDTO.class);
    }

    @DeleteMapping("/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "Cliente deletado com sucesso!";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> authenticate(@RequestBody ClienteLoginDto clienteLoginDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        clienteLoginDto.getEmail(),
                        clienteLoginDto.getSenha()
                )
        );

        Cliente cliente = clienteRepository.findByEmail(clienteLoginDto.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(cliente);

        LoginDto loginDto = new LoginDto();

        loginDto.setToken(jwtToken);
        loginDto.setExpiraEm(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginDto);

    }

}
