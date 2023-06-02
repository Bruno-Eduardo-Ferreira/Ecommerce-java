package br.edu.utfpr.ecommerce.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PedidoDTO {
    @NotBlank(message = "Obrigatório")

    @NotBlank(message = "Obrigatório")
    private Boolean isopen;

    @NotBlank(message = "Obrigatório")
    private UUID carrinhoId;

}
