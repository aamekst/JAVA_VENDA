package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.Infrastructure.repository.VendaRepository;
import com.api_vendinha.api.domain.dtos.request.VendaRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendaResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import com.api_vendinha.api.domain.entities.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de vendas.
 *
 * Esta classe fornece a implementação dos métodos definidos na interface VendaServiceInterface,
 * lidando com a lógica de negócios relacionada às vendas, como criar e atualizar vendas.
 */
@Service
public class VendaServiceImpl implements VendaServiceInterface {

    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;

    @Autowired
    public VendaServiceImpl(UserRepository userRepository, ProdutoRepository produtoRepository, VendaRepository vendaRepository) {
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
    }

    @Override
    public VendaResponseDto save(VendaRequestDto vendaRequestDto) {

        // Recupera o produto e verifica se ele existe
        Produto produto = produtoRepository.findById(vendaRequestDto.getProduto_id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Verifica se há estoque suficiente
        if (produto.getQuantidade() < vendaRequestDto.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para a venda.");
        }

        // Recupera o usuário e verifica se ele existe
        User user = userRepository.findById(vendaRequestDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Cria uma nova venda
        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setUser(user);
        venda.setQuantidade(vendaRequestDto.getQuantidade());

        // Calcula o valor total da venda com base no preço unitário do produto
        double valorTotal = vendaRequestDto.getQuantidade() * produto.getPreco();
        venda.setPreco(valorTotal);

        // Atualiza o estoque do produto
        produto.setQuantidade(produto.getQuantidade() - vendaRequestDto.getQuantidade());
        produtoRepository.save(produto);

        // Salva a venda no banco de dados
        Venda savedVenda = vendaRepository.save(venda);

        // Cria o DTO de resposta com as informações da venda
        VendaResponseDto vendaResponseDto = new VendaResponseDto();
        vendaResponseDto.setId(savedVenda.getId());
        vendaResponseDto.setPreco(savedVenda.getPreco());
        vendaResponseDto.setQuantidade(savedVenda.getQuantidade());
        vendaResponseDto.setNomeProduto(savedVenda.getProduto().getNome());
        vendaResponseDto.setNomeCliente(savedVenda.getUser().getName());

        // Retorna o DTO com as informações da venda salva
        return vendaResponseDto;
    }

    @Override
    public List<VendaResponseDto> buscarTodos() {
        List<Venda> vendas = vendaRepository.findAll();

        // Mapear a lista de Venda para uma lista de VendaResponseDto
        List<VendaResponseDto> vendaResponseDtos = vendas.stream().map(venda -> {
            VendaResponseDto vendaResponseDto = new VendaResponseDto();

            vendaResponseDto.setId(venda.getId());
            vendaResponseDto.setNomeProduto(venda.getNomeProduto());
            vendaResponseDto.setPreco(venda.getPreco());
            vendaResponseDto.setQuantidade(venda.getQuantidade());
            vendaResponseDto.setNomeCliente(venda.getUser().getName());


            return vendaResponseDto; // Este retorno estava faltando
        }).collect(Collectors.toList());

        // Retornar a lista de VendaResponseDto
        return vendaResponseDtos;
    }
}
