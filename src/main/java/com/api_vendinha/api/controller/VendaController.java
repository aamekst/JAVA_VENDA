package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.VendaRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.dtos.response.VendaResponseDto;
import com.api_vendinha.api.domain.service.VendaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaServiceInterface vendaServiceInterface;
    @Autowired
    public VendaController(VendaServiceInterface vendaServiceInterface) {
        this.vendaServiceInterface = vendaServiceInterface;
    }

   @PostMapping
   public VendaResponseDto salvar(@RequestBody VendaRequestDto vendaRequestDto) {
      return vendaServiceInterface.save(vendaRequestDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Retorna apenas a mensagem da exceção e o status HTTP 400 (Bad Request)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/todos")
    public List<VendaResponseDto> buscarTodos() {
        return vendaServiceInterface.buscarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public VendaResponseDto atualizar(@PathVariable Integer id, @RequestBody VendaRequestDto vendaRequestDto) {
        return vendaServiceInterface.atualizar(id,vendaRequestDto);

    }

    @GetMapping("/buscar/{id}")
    public VendaResponseDto buscar(@PathVariable Integer id){
        return vendaServiceInterface.buscar(id);
    }

    @DeleteMapping("/deletar/{id}")
    public VendaResponseDto deletar(@PathVariable Integer id) {
        return vendaServiceInterface.deletar(id);

    }
}
