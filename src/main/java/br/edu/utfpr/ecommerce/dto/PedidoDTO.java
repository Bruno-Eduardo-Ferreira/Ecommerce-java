package br.edu.utfpr.ecommerce.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PedidoDTO {
    @NotBlank(message = "Obrigatório")

    @NotBlank(message = "Obrigatório")
    private Boolean isopen;

    @NotBlank(message = "Obrigatório")
    private LocalDate date;

    @NotBlank(message = "Obrigatório")
    private UUID carrinhoId;

}
