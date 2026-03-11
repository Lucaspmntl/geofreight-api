package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import me.lucaspmntl.geofreight.dto.brasilapi.BrasilApiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@WireMockTest(httpPort = 8089)
@SpringBootTest(value = "brasilapi.url=http://localhost:8089/api/cep/v2/")
class BrasilApiServiceTest {

    @Autowired
    private BrasilApiService brasilApiService;

    @Test
    void shouldReturnCoordinatesWhenValidCep() {
        String cep = "76873228";
        String json = """
                {
                  "cep": "76873228",
                  "state": "RO",
                  "city": "Ariquemes",
                  "neighborhood": "Setor 02",
                  "street": "Rua Uirapuru",
                  "service": "open-cep",
                  "location": {
                    "type": "Point",
                    "coordinates": {
                      "longitude": "-63.0304959",
                      "latitude": "-9.9173597"
                    }
                  }
                }
                """;

        stubFor(get
                (urlPathEqualTo("/api/cep/v2/" + cep))
                .willReturn(okJson(json)));

        BrasilApiDTO response = brasilApiService.getCoordinatesByCep(cep);

        assertNotNull(response);
        assertEquals(-63.0304959, response.location().coordinates().longitude());
        assertEquals(-9.9173597, response.location().coordinates().latitude());
    }
}