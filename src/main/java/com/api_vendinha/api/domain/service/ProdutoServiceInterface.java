package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;

public interface ProdutoServiceInterface {

    ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto);

    ProdutoResponseDto atualizar(Integer id, ProdutoRequestDto produtoRequestDto);

}
