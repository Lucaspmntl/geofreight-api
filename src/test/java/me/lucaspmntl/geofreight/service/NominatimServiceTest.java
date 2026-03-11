package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import me.lucaspmntl.geofreight.dto.NominatimDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@WireMockTest(httpPort = 8089)
@SpringBootTest//(value = "nominatim.url=http://localhost:8089")
class NominatimServiceTest {

    @Autowired
    NominatimService nominatimService;

    @Test
    void shouldReturnCoordinatesWhenValidAddress(){
        String query = "Ariquemes, Uirapuru RO";
        String json = """
                     [
                         {
                           "place_id": 11827350,
                           "licence": "Data © OpenStreetMap contributors, ODbL 1.0. http://osm.org/copyright",
                           "osm_type": "way",
                           "osm_id": 184213648,
                           "lat": "-9.9173597",
                           "lon": "-63.0304959",
                           "class": "highway",
                           "type": "residential",
                           "place_rank": 26,
                           "importance": 0.05338729304951549,
                           "addresstype": "road",
                           "name": "Rua Uirapuru",
                           "display_name": "Rua Uirapuru, Ariquemes, Região Geográfica Imediata de Ariquemes, Região Geográfica Intermediária de Porto Velho, Rondônia, Região Norte, 76873-238, Brasil",
                           "boundingbox": ["-9.9213261", "-9.9133934", "-63.0305617", "-63.0304482"]
                         }
                     ]
                     """;

        stubFor(get(urlPathEqualTo("/search"))
                .withQueryParam("q", containing(query))
                .willReturn(okJson(json)));

        List<NominatimDTO> response = nominatimService.getCoordinatesByAddress(query);

        assertNotNull(response);
        assertEquals(response.size(), 1);
        assertEquals(response.getFirst().lat(), "-9.9173597");
        assertEquals(response.getFirst().lon(), "-63.0304959");
    }
}

