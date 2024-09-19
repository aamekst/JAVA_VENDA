package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para representar os dados necessários para criar ou atualizar um usuário.
 */
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias pelo JPA e outras operações.
public class UserRequestDto {

    /**
     * Nome do usuário.
     *
     * Este campo é obrigatório e será utilizado para criar ou atualizar um usuário no sistema.
     */

    private String name;
    private String email;
    private String password;
    private String cpfcnpj;
    private Boolean is_active;
    private List<ProdutoRequestDto> produtoRequestDtos;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProdutoRequestDto> getProdutoRequestDtos() {
        return produtoRequestDtos;
    }

    public void setProdutoRequestDtos(List<ProdutoRequestDto> produtoRequestDtos) {
        this.produtoRequestDtos = produtoRequestDtos;
    }
}
