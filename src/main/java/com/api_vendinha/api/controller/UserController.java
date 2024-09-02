package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Controlador REST para gerenciar operações relacionadas aos usuários.
 */
@RestController
@RequestMapping("/api/users") // Define o caminho base para as requisições deste controlador.
public class UserController {

    // Injeção de dependência do serviço de usuários.
    private final UserServiceInterface userService;

    /**
     * Construtor para injeção de dependência do serviço de usuários.
     *
     * @param userService O serviço de usuários a ser injetado.
     */
    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Método para salvar um novo usuário.
     *
     * @param userRequestDto DTO que contém os dados do usuário a ser salvo.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado.
     */
    @PostMapping // Define que este método lida com requisições HTTP POST.
    public UserResponseDto salvar(@RequestBody UserRequestDto userRequestDto) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return userService.save(userRequestDto);
    }

    @PutMapping("/atualiza/{id}")
    public UserResponseDto atualizar(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return userService.atualizar(id,userRequestDto);

    }
    @PutMapping("/status/{id}")
    public UserResponseDto desativar(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return userService.desativar(id,userRequestDto);

    }

    @GetMapping("/buscar/{id}")
    public UserResponseDto buscar(@PathVariable Long id) {
        return userService.buscar(id);
    }


    @DeleteMapping("/deletar/{id}")
    public UserResponseDto deletar(@PathVariable Long id) {
        return userService.deletar(id);

    }

    @GetMapping("/todos")
    public List<UserResponseDto> buscarTodos() {
        return userService.buscarTodos();
    }


}
