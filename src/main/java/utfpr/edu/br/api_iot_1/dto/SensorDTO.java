package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SensorDTO(
    @NotBlank(message = "O nome do sensor não pode estar vazio.")
    @Size(min = 3, max = 50, message = "O nome do sensor deve ter entre 3 e 50 caracteres.")
    String nome, 
    
    @NotBlank(message = "O tipo do sensor não pode estar vazio.")
    @Size(min = 3, max = 30, message = "O tipo do sensor deve ter entre 3 e 30 caracteres.")
    String tipo, 
    
    boolean ligado, 
    
    @NotNull(message = "O ID do dispositivo não pode ser nulo.")
    long dispositivo_id
) {

}
