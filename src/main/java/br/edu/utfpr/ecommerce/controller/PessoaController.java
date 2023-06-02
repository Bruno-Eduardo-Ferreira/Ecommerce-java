package br.edu.utfpr.ecommerce.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.ecommerce.dto.PessoaDTO;
import br.edu.utfpr.ecommerce.model.Pessoa;
import br.edu.utfpr.ecommerce.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Obter todas as pessoas da API
    @GetMapping({ "", "/" })
    public Iterable<Pessoa> getPessoas() {
        return pessoaRepository.findAll();
    }

    // Obter 1 pessoa pelo seu id
    @GetMapping("/{id}")
    public Optional<Pessoa> getPessoa(@PathVariable String id) {
        return pessoaRepository.findById(UUID.fromString(id));
    }

    // Criar uma pessoa
    @PostMapping({ "", "/" })
    public ResponseEntity<Object> create(@RequestBody PessoaDTO pessoaDTO) {

        // Validar se o e-mail existe no banco de dados
        if (pessoaRepository.existsByEmail(pessoaDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conflito: E-mail já existe");
        }

        var entity = new Pessoa();

        // BeanUtils vai copiar os dados de PessoaDTO para a entity do tipo Pessoa
        BeanUtils.copyProperties(pessoaDTO, entity);
        entity.setId(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(pessoaDTO.getPassword()));;

        try {
            // Salva a pessoa no DB
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
