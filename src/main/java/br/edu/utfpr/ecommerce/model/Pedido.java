package br.edu.utfpr.ecommerce.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    private UUID id;

    @Column(name = "is_open")
    private Boolean isopen;

    // Um pedido para um carrinho
    @OneToOne
    private Carrinho carrinho;

}
