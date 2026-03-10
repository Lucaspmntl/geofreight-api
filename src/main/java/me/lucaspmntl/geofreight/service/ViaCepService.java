package me.lucaspmntl.geofreight.service;

import me.lucaspmntl.geofreight.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "via-cep", url = "viacep.com.br/ws")
/**
 * Cliente Feign para integração com o serviço ViaCEP.
 * Responsável por obter os dados cadastrais de endereço (logradouro, bairro, cidade e UF)
 * a partir de um CEP informado, garantindo a validação inicial da localidade.
 */
public interface ViaCepService {

    /**
     * @param cep -> CEP informado
     * @return {@link Address} -> Endereço encontrado
     */
    @GetMapping("/{cep}/json")
    public Address getAddressByCep(@PathVariable String cep);
}