package utfpr.edu.br.api_iot_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.api_iot_1.dto.DispositivoDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Dispositivo;
import utfpr.edu.br.api_iot_1.repository.DispositivoRepository;
import utfpr.edu.br.api_iot_1.repository.GatewayRepository;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private GatewayRepository gatewayRepository;

    public Dispositivo create(DispositivoDTO dispositivoDTO) {
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dispositivoDTO, dispositivo);

        var gateway = gatewayRepository.findById(dispositivoDTO.gateway_id());

        if (gateway.isPresent())
            dispositivo.setGateway(gateway.get());

        return dispositivoRepository.save(dispositivo);
    }

    public List<Dispositivo> getAll() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getById(long id) {
        return dispositivoRepository.findById(id);
    }

    public List<Dispositivo> findByGatewayId(long id) {
        return dispositivoRepository.findByGatewayId(id);
    }

    public Dispositivo update(long id, DispositivoDTO dto) throws NotFoundException {
        var res = dispositivoRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não foi encontrada!");
        }

        var dispositivo = res.get();
        var gateway = gatewayRepository.findById(dto.gateway_id());
        if (gateway.isEmpty())
            throw new NotFoundException("Gateway" + dto.gateway_id() + " Não foi encontrada!");
        dispositivo.setNome(dto.nome());
        dispositivo.setLocalizacao(dto.localizacao());
        dispositivo.setGateway(gateway.get());

        return dispositivoRepository.save(dispositivo);
    }

    public void delete(long id) throws NotFoundException {
        var res = dispositivoRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não foi encontrada!");
        }

        dispositivoRepository.delete(res.get());
    }
}
