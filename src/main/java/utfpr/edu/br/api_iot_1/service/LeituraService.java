package utfpr.edu.br.api_iot_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.api_iot_1.dto.LeituraDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Leitura;
import utfpr.edu.br.api_iot_1.model.Sensor;
import utfpr.edu.br.api_iot_1.repository.LeituraRepository;
import utfpr.edu.br.api_iot_1.repository.SensorRepository;

@Service
public class LeituraService {
    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public Leitura create(LeituraDTO leituraDTO) {
        var leitura = new Leitura();
        BeanUtils.copyProperties(leituraDTO, leitura);

        Sensor sensor = sensorRepository.findById(leituraDTO.sensor_id()).get();
        
        if (sensor == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        leitura.setSensor(sensor);

        return leituraRepository.save(leitura);
    }

    public List<Leitura> getAll() {
        return leituraRepository.findAll();
    }

    public Optional<Leitura> getById(long id) {
        return leituraRepository.findById(id);
    }

    public List<Leitura> findBySensorId(long id) {
        return leituraRepository.findBySensorId(id);
    }

    public Leitura update(long id, LeituraDTO dto) throws NotFoundException {
        var res = leituraRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Leitura " + id + " não foi encontrada!");
        }

        var leitura = res.get();
        var sensor = sensorRepository.findById(dto.sensor_id());
        leitura.setValor(dto.valor());
        leitura.setData(dto.data());
        leitura.setSensor(sensor.get());    

        return leituraRepository.save(leitura);
    }

    public void delete(long id) throws NotFoundException {
        var res = leituraRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Leitura " + id + " não foi encontrada!");
        }

        leituraRepository.delete(res.get());
    }
}
