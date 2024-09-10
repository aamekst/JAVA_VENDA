package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.service.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoServiceInterface produtoService;
    @Autowired
    public ProdutoController(ProdutoServiceInterface produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoResponseDto salvar(@RequestBody ProdutoRequestDto produtoRequestDto) {
        return produtoService.save(produtoRequestDto);
    }

    @PutMapping("/atualizar/{id}")
    public ProdutoResponseDto atualizar(@PathVariable Integer id, @RequestBody ProdutoRequestDto produtoRequestDto) {
        return produtoService.atualizar(id,produtoRequestDto);

    }

}
