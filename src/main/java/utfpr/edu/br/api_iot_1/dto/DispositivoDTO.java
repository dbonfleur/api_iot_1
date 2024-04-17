package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DispositivoDTO(
    @NotBlank(message = "O nome do dispositivo não pode estar vazio.")
    @Size(min = 3, max = 100, message = "O nome do dispositivo deve ter entre 3 e 100 caracteres.")
    String nome,

    @NotBlank(message = "A localização do dispositivo não pode estar vazia.")
    @Size(min = 3, max = 200, message = "A localização do dispositivo deve ter entre 3 e 200 caracteres.")
    String localizacao,

    @NotNull(message = "O ID do gateway não pode ser nulo.")
    long gateway_id
) {

}
