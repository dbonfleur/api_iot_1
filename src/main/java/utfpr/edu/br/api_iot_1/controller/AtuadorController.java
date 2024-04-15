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

import utfpr.edu.br.api_iot_1.dto.AtuadorDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Atuador;
import utfpr.edu.br.api_iot_1.service.AtuadorService;

@RestController
@RequestMapping("/atuador")
public class AtuadorController {
    @Autowired
    private AtuadorService atuadorService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AtuadorDTO dto) {
        try {
            var res = atuadorService.create(dto);
            /**
             * Seta o status para 201 e retorna a atuador em JSON.
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
     * Método para obter todas as atuadors do DB.
     */
    @GetMapping
    public List<Atuador> getAll() {
        return atuadorService.getAll();
    }

    @GetMapping("/dispositivo/{id}")
    public List<Atuador> findByDispositivoId(@PathVariable("id") long id) {
        return atuadorService.findByDispositivoId(id);
    }

    /**
     * Devolver atuador por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var atuador = atuadorService.getById(id);
        return atuador.isPresent()
                ? ResponseEntity.ok().body(atuador.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Atualizar atuador por id.
     * 
     * @param id
     * @param dto
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody AtuadorDTO dto) {
        try {
            return ResponseEntity.ok().body(atuadorService.update(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            atuadorService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
