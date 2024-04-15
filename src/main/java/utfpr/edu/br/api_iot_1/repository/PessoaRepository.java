package utfpr.edu.br.api_iot_1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.edu.br.api_iot_1.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByNome(String nome);
}
