package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GatewayDTO(
    @NotBlank(message = "A descrição não pode estar vazia.")
    @Size(min = 3, max = 200, message = "A descrição deve ter entre 3 e 200 caracteres.")
    String descricao, 
    
    @NotBlank(message = "O endereço não pode estar vazio.")
    @Size(min = 3, max = 200, message = "O endereço deve ter entre 3 e 200 caracteres.")
    String endereco, 
    
    @NotNull(message = "O ID da pessoa não pode ser nulo.")
    Long pessoa_id
) {
    
}