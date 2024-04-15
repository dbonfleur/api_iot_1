package utfpr.edu.br.api_iot_1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.api_iot_1.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByDispositivoId(long id);
}
