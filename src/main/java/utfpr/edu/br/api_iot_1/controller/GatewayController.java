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

import utfpr.edu.br.api_iot_1.dto.GatewayDTO;
import utfpr.edu.br.api_iot_1.exception.NotFoundException;
import utfpr.edu.br.api_iot_1.model.Gateway;
import utfpr.edu.br.api_iot_1.service.GatewayService;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody GatewayDTO dto) {
        try {
            var res = gatewayService.create(dto);
            /**
             * Seta o status para 201 e retorna a gateway em JSON.
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
     * Método para obter todas as gateways do DB.
     */
    @GetMapping
    public List<Gateway> getAll() {
        return gatewayService.getAll();
    }

    @GetMapping("/pessoa/{id}")
    public List<Gateway> getGatewaysByPessoa(@PathVariable("id") long id) {
        return gatewayService.getGatewaysByPessoa(id);
    }

    /**
     * Devolver gateway por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var gatway = gatewayService.getById(id);
        return gatway.isPresent()
                ? ResponseEntity.ok().body(gatway.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Atualizar gateway por id.
     * 
     * @param id
     * @param dto
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody GatewayDTO dto) {
        try {
            return ResponseEntity.ok().body(gatewayService.update(id, dto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            gatewayService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
