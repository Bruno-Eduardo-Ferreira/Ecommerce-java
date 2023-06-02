package br.edu.utfpr.ecommerce.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_carrinho")
public class Carrinho {
    @Id
    private UUID id;

    // Um carrinho para muitos produtos
    @OneToMany
    private List<Produto> products;

    // Um carrinho para uma pessoa
    @OneToOne
    private Pessoa pessoa;

}
