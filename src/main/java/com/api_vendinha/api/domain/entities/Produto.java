package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Produtos")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Produto {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;


   @Column(nullable = false)
   private String nome;

   @Column(name = "quantidade", nullable = false)
   private Integer quantidade;

   @Column(name = "preco", nullable = false)
   private Double preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
