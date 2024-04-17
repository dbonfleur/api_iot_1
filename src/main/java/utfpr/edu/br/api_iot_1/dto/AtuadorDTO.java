package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtuadorDTO(
    @NotBlank(message = "O nome do atuador não pode estar vazio.")
    String nome,

    @NotNull(message = "O ID do dispositivo não pode ser nulo.")
    long dispositivo_id
) {

}