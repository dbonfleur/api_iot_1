package utfpr.edu.br.api_iot_1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utfpr.edu.br.api_iot_1.dto.SensorDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Sensor;
import utfpr.edu.br.api_iot_1.service.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody SensorDTO dto) {
        try {
            var res = sensorService.create(dto);
            /**
             * Seta o status para 201 e retorna a sensor em JSON.
             */
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            /**
             * Seta o status para 400 (Bad Request) e devolve a mensagem
             * da exeção lançada.
             */
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Método para obter todas as sensors do DB.
     */
    @GetMapping
    public List<Sensor> getAll() {
        return sensorService.getAll();
    }

    @GetMapping("/dispositivo/{id}")
    public List<Sensor> findByDispositivoId(@PathVariable("id") long id) {
        return sensorService.findByDispositivoId(id);
    }

    /**
     * Devolver sensor por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var sensor = sensorService.getById(id);
        return sensor.isPresent()
                ? ResponseEntity.ok().body(sensor.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Atualizar sensor por id.
     * 
     * @param id
     * @param dto
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody SensorDTO dto) {
        try {
            return ResponseEntity.ok().body(sensorService.update(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            sensorService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
