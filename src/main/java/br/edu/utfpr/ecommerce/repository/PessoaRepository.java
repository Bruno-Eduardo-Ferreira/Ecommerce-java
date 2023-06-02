package br.edu.utfpr.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.edu.utfpr.ecommerce.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, UUID> {
        public boolean existsByEmail(String email);
        public Optional<Pessoa> findByEmail(String email);

}
