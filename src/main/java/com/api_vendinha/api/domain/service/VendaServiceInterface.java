package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.VendaRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendaResponseDto;

/**
 * Interface que define os serviços relacionados a usuários.
 *
 * Esta interface fornece um contrato para a implementação dos serviços de usuários,
 * especificando os métodos que devem ser fornecidos pelas classes que a implementam.
 */
public interface VendaServiceInterface {


    VendaResponseDto save(VendaRequestDto VendaRequestDto);
}
