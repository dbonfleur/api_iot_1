package utfpr.edu.br.api_iot_1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.api_iot_1.model.Leitura;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findBySensorId(long id);
}
