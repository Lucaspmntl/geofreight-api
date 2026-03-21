package me.lucaspmntl.geofreight.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import me.lucaspmntl.geofreight.exception.ExternalIntegrationException;
import org.springframework.stereotype.Component;

@Component
public class GlobalFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        String apiName;

        if (methodKey.contains("ViaCep"))
            apiName = "ViaCep";
        else {
            apiName = "Melhor Envio";
        }

        switch (response.status()) {
            case 400:
                return new ExternalIntegrationException("Dados inválidos enviados para a integração: " + apiName);
            case 401:
                return new ExternalIntegrationException("Falha de autenticação (Token/Chave) na integração com: " + apiName);
            case 404:
                return new ExternalIntegrationException("Recurso não encontrado na API: " + apiName);
            case 500:
            case 503:
                return new ExternalIntegrationException("A API do " + apiName + " está temporariamente fora do ar.");
            default:
                return new ExternalIntegrationException("Erro desconhecido na comunicação com " + apiName);
        }
    }
}