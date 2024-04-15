package utfpr.edu.br.api_iot_1.model;

import java.sql.Date;

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
@Table(name = "tb_leitura") // Define nome tabela
@Data
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "valor", nullable = false)
    private long valor;

    @Column(name = "data", nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;
}
