package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service


public class ProdutoServiceImpl implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;
    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto) {
        // Cria uma nova instância de produto.
        Produto produto = new Produto();
        // Define o nome do produto a partir do DTO.
        produto.setNome(produtoRequestDto.getNome());
        produto.setQuantidade(produtoRequestDto.getQuantidade());
        produto.setPreco(produtoRequestDto.getPreco());



        Produto savedproduto = produtoRepository.save(produto);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(savedproduto.getId());
        produtoResponseDto.setNome(savedproduto.getNome());
        produtoResponseDto.setQuantidade(savedproduto.getQuantidade());
        produtoResponseDto.setPreco(savedproduto.getPreco());


        return produtoResponseDto;
    }

    @Override
    public ProdutoResponseDto atualizar (Integer id, ProdutoRequestDto produtoRequestDto) {
        Produto exist = produtoRepository.findById(id).orElseThrow();

        exist.setNome(produtoRequestDto.getNome());
        exist.setQuantidade(produtoRequestDto.getQuantidade());
        exist.setPreco(produtoRequestDto.getPreco());

        Produto savedexist = produtoRepository.save(exist);

        ProdutoResponseDto existResponseDto = new ProdutoResponseDto();
        existResponseDto.setId(id);
        existResponseDto.setNome(savedexist.getNome());
        existResponseDto.setQuantidade(savedexist.getQuantidade());
        existResponseDto.setPreco(savedexist.getPreco());

        return existResponseDto;
    }

}
