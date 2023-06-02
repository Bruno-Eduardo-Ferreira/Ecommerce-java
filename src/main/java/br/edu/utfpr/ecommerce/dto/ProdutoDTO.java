package br.edu.utfpr.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoDTO {
    @NotBlank(message = "Obrigatório")

    @Size(min = 10, message = "Min 10 caracteres")
    private String name;

    @NotBlank(message = "Obrigatório")
    private float value;

    @Size(min = 10, message = "Min 10 caracteres")
    private String description;

}
