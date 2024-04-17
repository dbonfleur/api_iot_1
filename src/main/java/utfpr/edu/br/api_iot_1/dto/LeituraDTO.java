package utfpr.edu.br.api_iot_1.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LeituraDTO(
    @NotNull(message = "O valor não pode ser nulo!")
    @Min(value = 0, message = "O valor deve ser maior ou igual a zero.")
    @Max(value = 1000, message = "O valor não pode exceder 1000.")
    long valor,

    @NotNull(message = "A data não pode ser nula!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date data,

    @NotNull(message = "O ID do sensor não pode ser nulo.")
    long sensor_id
) {
    
}
