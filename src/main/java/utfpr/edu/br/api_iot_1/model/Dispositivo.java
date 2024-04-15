package utfpr.edu.br.api_iot_1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // Entidade gerenciada pelo JPA
@Table(name = "tb_dispositivo") // Define nome tabela
@Data
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "gateway_id")
    private Gateway gateway;
}
