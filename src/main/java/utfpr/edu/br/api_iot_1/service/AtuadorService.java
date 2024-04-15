package utfpr.edu.br.api_iot_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.api_iot_1.dto.AtuadorDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Atuador;
import utfpr.edu.br.api_iot_1.repository.AtuadorRepository;
import utfpr.edu.br.api_iot_1.repository.DispositivoRepository;

@Service
public class AtuadorService {
    @Autowired
    private AtuadorRepository atuadorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Atuador create(AtuadorDTO atuadorDTO) {
        var atuador = new Atuador();
        BeanUtils.copyProperties(atuadorDTO, atuador);

        var dispositivo = dispositivoRepository.findById(atuadorDTO.dispositivo_id());

        if (dispositivo.isPresent())
            atuador.setDispositivo(dispositivo.get());

        return atuadorRepository.save(atuador);
    }

    public List<Atuador> getAll() {
        return atuadorRepository.findAll();
    }

    public Optional<Atuador> getById(long id) {
        return atuadorRepository.findById(id);
    }

    public List<Atuador> findByDispositivoId(long id) {
        return atuadorRepository.findByDispositivoId(id);
    }

    public Atuador update(long id, AtuadorDTO dto) throws NotFoundException {
        var res = atuadorRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Atuador " + id + " não foi encontrada!");
        }

        var atuador = res.get();
        var dispositivo = dispositivoRepository.findById(dto.dispositivo_id());
        if (dispositivo.isEmpty())
            throw new NotFoundException("Dispositivo " + dto.dispositivo_id() + " Não foi encontrado!");
        atuador.setNome(dto.nome());
        atuador.setDispositivo(dispositivo.get());

        return atuadorRepository.save(atuador);
    }

    public void delete(long id) throws NotFoundException {
        var res = atuadorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Atuador " + id + " não foi encontrada!");
        }

        atuadorRepository.delete(res.get());
    }
}
