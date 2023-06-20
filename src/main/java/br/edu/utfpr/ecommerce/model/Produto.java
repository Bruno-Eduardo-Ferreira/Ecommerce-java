package br.edu.utfpr.ecommerce.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    private UUID id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "value")
    private Double value;

    @Column(name = "description", length = 255)
    private String description;

}
