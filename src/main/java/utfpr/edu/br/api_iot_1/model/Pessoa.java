package utfpr.edu.br.api_iot_1.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "tb_pessoa")
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @Column(name = "email", length = 80,  nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 255, nullable = false)
    private String senha;
}
