package br.edu.utfpr.ecommerce.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarrinhoDTO {
    @NotBlank(message = "Obrigatório")

    @NotBlank(message = "Obrigatório")
    private List<UUID> productsIds;

    @NotBlank(message = "Obrigatório")
    private UUID pessoaId;

}
