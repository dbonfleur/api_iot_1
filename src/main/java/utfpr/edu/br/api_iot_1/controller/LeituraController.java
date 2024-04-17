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

import jakarta.validation.Valid;
import utfpr.edu.br.api_iot_1.dto.LeituraDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Leitura;
import utfpr.edu.br.api_iot_1.service.LeituraService;

@RestController
@RequestMapping("/leitura")
public class LeituraController {
    @Autowired
    private LeituraService leituraService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody LeituraDTO dto) {
        try {
            var res = leituraService.create(dto);
            /**
             * Seta o status para 201 e retorna a leitura em JSON.
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
     * Método para obter todas as leituras do DB.
     */
    @GetMapping
    public List<Leitura> getAll() {
        return leituraService.getAll();
    }

    @GetMapping("/sensor/{id}")
    public List<Leitura> findBySensorId(@PathVariable("id") long id) {
        return leituraService.findBySensorId(id);
    }

    /**
     * Devolver leitura por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var leitura = leituraService.getById(id);
        return leitura.isPresent()
                ? ResponseEntity.ok().body(leitura.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Atualizar leitura por id.
     * 
     * @param id
     * @param dto
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @Valid @RequestBody LeituraDTO dto) {
        try {
            return ResponseEntity.ok().body(leituraService.update(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            leituraService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
