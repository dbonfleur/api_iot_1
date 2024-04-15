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

import utfpr.edu.br.api_iot_1.dto.DispositivoDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Dispositivo;
import utfpr.edu.br.api_iot_1.service.DispositivoService;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody DispositivoDTO dto) {
        try {
            var res = dispositivoService.create(dto);
            /**
             * Seta o status para 201 e retorna a dispositivo em JSON.
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
     * Método para obter todas as dispositivos do DB.
     */
    @GetMapping
    public List<Dispositivo> getAll() {
        return dispositivoService.getAll();
    }

    @GetMapping("/gateway/{id}")
    public List<Dispositivo> findByGatewayId(@PathVariable("id") long id) {
        return dispositivoService.findByGatewayId(id);
    }

    /**
     * Devolver dispositivo por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var dispositivo = dispositivoService.getById(id);
        return dispositivo.isPresent()
                ? ResponseEntity.ok().body(dispositivo.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Atualizar dispositivo por id.
     * 
     * @param id
     * @param dto
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody DispositivoDTO dto) {
        try {
            return ResponseEntity.ok().body(dispositivoService.update(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            dispositivoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
