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
@Table(name = "tb_sensor") // Define nome tabela
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "tipo", length = 300, nullable = false)
    private String tipo;

    @Column(name = "ligado")
    private boolean ligado = false;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;
}
