package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service


public class ProdutoServiceImpl implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProdutoServiceImpl(
            ProdutoRepository produtoRepository,
            UserRepository userRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto) {

        // Cria uma nova instância de produto.

        User user = userRepository.findById(Long.valueOf(produtoRequestDto.getUser_id())).orElseThrow();

                Produto produto = new Produto();
        // Define o nome do produto a partir do DTO.
        produto.setNome(produtoRequestDto.getNome());
        produto.setQuantidade(produtoRequestDto.getQuantidade());
        produto.setPreco(produtoRequestDto.getPreco());
        produto.setUser(user);

        Produto savedproduto = produtoRepository.save(produto);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(savedproduto.getId());
        produtoResponseDto.setNome(savedproduto.getNome());
        produtoResponseDto.setQuantidade(savedproduto.getQuantidade());
        produtoResponseDto.setPreco(savedproduto.getPreco());
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setCpfcnpj(user.getCpfcnpj());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setIs_active(user.getIs_active());
        produtoResponseDto.setUserResponseDto(
               userResponseDto
        );


        return produtoResponseDto;
    }

    @Override
    public ProdutoResponseDto atualizar(Integer id, ProdutoRequestDto produtoRequestDto) {
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

    @Override
    public ProdutoResponseDto buscartodos(Long id, ProdutoRequestDto produtoRequestDto) {
        return null;
    }


    @Override
    public List<ProdutoResponseDto> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        List<ProdutoResponseDto> produtoResponseDtos = produtos.stream().map(produto -> {
            ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();

            produtoResponseDto.setId(produto.getId());
            produtoResponseDto.setNome(produto.getNome());
            produtoResponseDto.setPreco(produto.getPreco());
            produtoResponseDto.setQuantidade(produto.getQuantidade());

            return produtoResponseDto;
        }).collect(Collectors.toList());


        return produtoResponseDtos;
    }

    public ProdutoResponseDto buscar(Integer id) {
        Produto exist = produtoRepository.findById(id).orElseThrow();
        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();

        produtoResponseDto.setId(exist.getId());
        produtoResponseDto.setNome(exist.getNome());
        produtoResponseDto.setQuantidade(exist.getQuantidade());
        produtoResponseDto.setPreco(exist.getPreco());

        return produtoResponseDto;
    }
}
