package utfpr.edu.br.api_iot_1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.api_iot_1.model.Atuador;

public interface AtuadorRepository extends JpaRepository<Atuador, Long> {
    List<Atuador> findByDispositivoId(long id);
}
