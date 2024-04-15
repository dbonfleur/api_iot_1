package utfpr.edu.br.api_iot_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.api_iot_1.dto.SensorDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Sensor;
import utfpr.edu.br.api_iot_1.repository.DispositivoRepository;
import utfpr.edu.br.api_iot_1.repository.SensorRepository;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Sensor create(SensorDTO sensorDTO) {
        var sensor = new Sensor();
        BeanUtils.copyProperties(sensorDTO, sensor);

        var dispositivo = dispositivoRepository.findById(sensorDTO.dispositivo_id());

        if (dispositivo.isPresent())
            sensor.setDispositivo(dispositivo.get());

        return sensorRepository.save(sensor);
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getById(long id) {
        return sensorRepository.findById(id);
    }

    public List<Sensor> findByDispositivoId(long id) {
        return sensorRepository.findByDispositivoId(id);
    }

    public Sensor update(long id, SensorDTO dto) throws NotFoundException {
        var res = sensorRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Sensor " + id + " não foi encontrada!");
        }

        var sensor = res.get();
        var dispositivo = dispositivoRepository.findById(dto.dispositivo_id());
        sensor.setNome(dto.nome());
        sensor.setLigado(dto.ligado());
        sensor.setTipo(dto.tipo());
        sensor.setDispositivo(dispositivo.get());

        return sensorRepository.save(sensor);
    }

    public void delete(long id) throws NotFoundException {
        var res = sensorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Sensor " + id + " não foi encontrada!");
        }

        sensorRepository.delete(res.get());
    }
}