package utfpr.edu.br.api_iot_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthDTO {
    @NotBlank
    @Size(min = 3)
    public String username;
    @NotBlank
    @Size(min = 3)
    public String password;
}
