package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de usuários.
 *
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProdutoRepository produtoRepository ) {
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     * <p>
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setCpfcnpj(userRequestDto.getCpfcnpj());
        user.setPassword(userRequestDto.getPassword());
        user.setIs_active(userRequestDto.getIs_active());
        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        List<Produto> produtos = userRequestDto.getProdutoRequestDtos().stream().map(dto->{
            Produto produto = new Produto();
            produto.setNome(dto.getNome());
            produto.setPreco(dto.getPreco());
            produto.setQuantidade(dto.getQuantidade());
            produto.setUser(savedUser);
            return produto;

        }).collect(Collectors.toList());

        produtoRepository.saveAll(produtos);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setCpfcnpj(savedUser.getCpfcnpj());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIs_active(savedUser.getIs_active());
        

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto atualizar(Long id, UserRequestDto userRequestDto) {
        User exist = userRepository.findById(id).orElseThrow();
        exist.setName(userRequestDto.getName());
        exist.setEmail(userRequestDto.getEmail());
        exist.setPassword(userRequestDto.getPassword());
        exist.setCpfcnpj(userRequestDto.getCpfcnpj());
        exist.setIs_active(userRequestDto.getIs_active());


        User savedUser = userRepository.save(exist);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(exist.getId());
        userResponseDto.setName(exist.getName());
        userResponseDto.setEmail(exist.getEmail());
        userResponseDto.setCpfcnpj(exist.getCpfcnpj());
        userResponseDto.setPassword(exist.getPassword());
        userResponseDto.setIs_active(savedUser.getIs_active());


        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto desativar(Long id, UserRequestDto userRequestDto) {
        User desa = userRepository.findById(id).orElseThrow();
        desa.setIs_active(userRequestDto.getIs_active());


        User savedUser = userRepository.save(desa);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(desa.getId());
        userResponseDto.setName(desa.getName());
        userResponseDto.setEmail(desa.getEmail());
        userResponseDto.setCpfcnpj(desa.getCpfcnpj());
        userResponseDto.setPassword(desa.getPassword());
        userResponseDto.setIs_active(desa.getIs_active());


        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto buscar(Long id) {
        User exist = userRepository.findById(id).orElseThrow();
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(exist.getId());
        userResponseDto.setName(exist.getName());
        userResponseDto.setEmail(exist.getEmail());
        userResponseDto.setCpfcnpj(exist.getCpfcnpj());
        userResponseDto.setPassword(exist.getPassword());
        userResponseDto.setIs_active(exist.getIs_active());

        return userResponseDto;
    }

    @Override
    public UserResponseDto buscartodos(Long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public List<UserResponseDto> buscarTodos() {
        List<User> users = userRepository.findAll();

        // Mapear a lista de User para uma lista de UserResponseDto
        List<UserResponseDto> userResponseDtos = users.stream().map(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();

            userResponseDto.setId(user.getId());
            userResponseDto.setName(user.getName());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setCpfcnpj(user.getCpfcnpj());
            userResponseDto.setPassword(user.getPassword());
            userResponseDto.setIs_active(user.getIs_active());

            return userResponseDto; // Este retorno estava faltando
        }).collect(Collectors.toList());

        // Retornar a lista de UserResponseDto
        return userResponseDtos;
    }

    @Override
    public UserResponseDto deletar(Long id, UserRequestDto userRequestDto) {
        return null;
    }


    @Override
    public UserResponseDto deletar(Long id) {
        userRepository.deleteById(id);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setMessage("User successfully deleted");

        return responseDto;
    }

}
