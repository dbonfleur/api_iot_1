package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaDTO(
    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    String nome, 
    
    @NotBlank(message = "O e-mail não pode estar vazio.")
    @Email(message = "Formato de e-mail inválido.")
    String email, 
    
    @NotBlank(message = "A senha não pode estar vazia.")
    @Size(min = 8, max = 30, message = "A senha deve ter entre 8 e 30 caracteres.")
    // Você poderia adicionar mais regras de complexidade aqui, como padrões de regex para incluir números, letras maiúsculas, minúsculas e caracteres especiais.
    String senha
) {

}
