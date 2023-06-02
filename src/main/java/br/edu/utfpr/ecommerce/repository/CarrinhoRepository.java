package br.edu.utfpr.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.edu.utfpr.ecommerce.model.Carrinho;

public interface CarrinhoRepository extends CrudRepository<Carrinho, UUID> {

}
