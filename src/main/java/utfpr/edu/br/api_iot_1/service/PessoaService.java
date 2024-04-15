package utfpr.edu.br.api_iot_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.api_iot_1.dto.PessoaDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Pessoa;
import utfpr.edu.br.api_iot_1.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(PessoaDTO pessoaDTO) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getById(long id) {
        return pessoaRepository.findById(id);
    }

    public Optional<Pessoa> findByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public Pessoa update(long id, PessoaDTO dto) throws NotFoundException {
        var res = pessoaRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não foi encontrada!");
        }

        var pessoa = res.get();
        pessoa.setNome(dto.nome());
        pessoa.setEmail(dto.email());

        return pessoaRepository.save(pessoa);
    }

    public void delete(long id) throws NotFoundException {
        var res = pessoaRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não foi encontrada!");
        }

        pessoaRepository.delete(res.get());
    }
}
