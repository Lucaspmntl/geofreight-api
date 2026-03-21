package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${viacep.url}")
/*
 * Cliente Feign para integração com o serviço ViaCEP.
 * Responsável por obter os dados cadastrais de endereço (logradouro, bairro, cidade e UF)
 * a partir de um CEP informado, garantindo a validação inicial da localidade.
 */
public interface ViaCepService {

    /**
     * @param cep -> CEP informado
     * @return {@link AddressDTO} -> Endereço encontrado
     */
    @GetMapping("/{cep}/json")
    public AddressDTO getAddressByCep(@PathVariable String cep);
}