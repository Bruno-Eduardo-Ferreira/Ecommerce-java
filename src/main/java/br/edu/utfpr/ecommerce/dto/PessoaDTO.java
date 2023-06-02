package br.edu.utfpr.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaDTO {
    @NotBlank(message = "Obrigatório")
    @Size(min = 4, message = "Min 4 caracteres")
    private String name;

    @NotBlank(message = "Obrigatório")
    @Size(min = 4, message = "Min 4 caracteres")
    private String password;

    @NotBlank(message = "Obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;
}
