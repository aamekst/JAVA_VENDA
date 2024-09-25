package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.User;
import com.api_vendinha.api.domain.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para a entidade User.
 *
 * Esta interface estende JpaRepository, o que fornece uma série de métodos prontos para realizar
 * operações de persistência no banco de dados para a entidade User.
 */
@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {


}
