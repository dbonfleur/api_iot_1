package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DispositivoDTO(
        @NotBlank
        @Size(min = 3)
        String nome,
        @NotBlank
        @Size(min = 3)
        String localizacao,
        long gateway_id
) {
    public DispositivoDTO {
        // Aqui você pode adicionar lógica de validação adicional, se necessário
        if (nome == null || localizacao == null) {
            throw new IllegalArgumentException("Nome e localização são obrigatórios");
        }
    }
}
