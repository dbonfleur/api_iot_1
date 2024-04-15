package utfpr.edu.br.api_iot_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.api_iot_1.dto.GatewayDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Gateway;
import utfpr.edu.br.api_iot_1.model.Pessoa;
import utfpr.edu.br.api_iot_1.repository.GatewayRepository;
import utfpr.edu.br.api_iot_1.repository.PessoaRepository;

@Service
public class GatewayService {
    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    // @Autowired
    // private DispositivoRepository dispositivoRepository;

    public Gateway create(GatewayDTO gatewayDTO) {
        var gateway = new Gateway();
        BeanUtils.copyProperties(gatewayDTO, gateway);

        Pessoa pessoa = pessoaRepository.findById(gatewayDTO.pessoa_id()).get();
        if (pessoa == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        gateway.setPessoa(pessoa);

        return gatewayRepository.save(gateway);
    }

    public List<Gateway> getAll() {
        return gatewayRepository.findAll();
    }

    public Optional<Gateway> getById(long id) {
        return gatewayRepository.findById(id);
    }

    public List<Gateway> getGatewaysByPessoa(long id) {
        return gatewayRepository.findByPessoaId(id);
    }

    public Gateway update(long id, GatewayDTO dto) throws NotFoundException {
        var res = gatewayRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Gateway " + id + " não foi encontrada!");
        }

        var gateway = res.get();
        var pessoa = pessoaRepository.findById(dto.pessoa_id());
        if (pessoa.isEmpty())
            throw new NotFoundException("Pessoa" + dto.pessoa_id() + " Não foi encontrada!");
        gateway.setDescricao(dto.descricao());
        gateway.setEndereco(dto.endereco());
        gateway.setPessoa(pessoa.get());

        return gatewayRepository.save(gateway);
    }

    public void delete(long id) throws NotFoundException {
        var res = gatewayRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Gateway " + id + " não foi encontrada!");
        }

        gatewayRepository.delete(res.get());
    }
}
