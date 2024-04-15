package utfpr.edu.br.api_iot_1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utfpr.edu.br.api_iot_1.model.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    List<Dispositivo> findByGatewayId(long id);
}
