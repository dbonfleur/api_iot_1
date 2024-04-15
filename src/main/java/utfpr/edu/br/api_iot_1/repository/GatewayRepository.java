package utfpr.edu.br.api_iot_1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utfpr.edu.br.api_iot_1.model.Gateway;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    List<Gateway> findByPessoaId(long id);
}