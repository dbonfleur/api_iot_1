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
@Table(name = "tb_gateway") // Define nome tabela
@Data
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "endereco", length = 100, nullable = false, unique = true)
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
}
