package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import me.lucaspmntl.northfreight.dto.AddressDTO;
import me.lucaspmntl.northfreight.service.ViaCepService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest(httpPort = 8089)
@SpringBootTest
@ActiveProfiles("test")
class ViaCepServiceTest {

    @Autowired
    private ViaCepService viaCepService;

    @Test
    void shouldReturnCorrectAddress() {
        String cep = "76873228";
        String json = """
                {
                  "cep": "76873-228",
                  "logradouro": "Rua Uirapuru",
                  "complemento": "de 1513/1514 a 1974/1975",
                  "unidade": "",
                  "bairro": "Setor 02",
                  "localidade": "Ariquemes",
                  "uf": "RO",
                  "estado": "Rondônia",
                  "regiao": "Norte",
                  "ibge": "1100023",
                  "gia": "",
                  "ddd": "69",
                  "siafi": "0007"
                }
                """;

        stubFor(get
                (urlPathEqualTo("/" + cep + "/json"))
                .willReturn(okJson(json)));

        AddressDTO response = viaCepService.getAddressByCep(cep);

        assertNotNull(response);
            assertEquals("76873-228", response.cep());
        assertEquals("Rua Uirapuru", response.street());
        assertEquals("de 1513/1514 a 1974/1975", response.complement());
        assertEquals("", response.unit());
        assertEquals("Setor 02", response.neighborhood());
        assertEquals("Ariquemes", response.city());
        assertEquals("RO", response.uf());
        assertEquals("Rondônia", response.state());
    }
}

