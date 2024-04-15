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

import utfpr.edu.br.api_iot_1.dto.PessoaDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Pessoa;
import utfpr.edu.br.api_iot_1.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PessoaDTO dto) {
        try {
            var res = pessoaService.create(dto);
            /**
             * Seta o status para 201 e retorna a pessoa em JSON.
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
     * Método para obter todas as pessoas do DB.
     */
    @GetMapping
    public List<Pessoa> getAll() {
        return pessoaService.getAll();
    }

    /**
     * Devolver pessoa por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var person = pessoaService.getById(id);
        return person.isPresent()
                ? ResponseEntity.ok().body(person.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Atualizar pessoa por id.
     * 
     * @param id
     * @param dto
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody PessoaDTO dto) {
        try {
            return ResponseEntity.ok().body(pessoaService.update(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            pessoaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
