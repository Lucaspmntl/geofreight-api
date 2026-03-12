package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import me.lucaspmntl.geofreight.dto.osrm.OsrmDistanceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest(httpPort = 8089)
@SpringBootTest
@ActiveProfiles("test")
class OsrmServiceTest {

    @Autowired
    private OsrmService osrmService;

    @Test
    void shouldReturnCorrectDistance() {
        String query = "-46.6558,-23.5588;-51.0664,-0.0347";
        String json = """
                {
                  "code": "Ok",
                  "routes": [
                    {
                      "legs": [
                        {
                          "steps": [],
                          "weight": 287336.3,
                          "summary": "",
                          "duration": 287335.4,
                          "distance": 3970317
                        }
                      ],
                      "weight_name": "routability",
                      "weight": 287336.3,
                      "duration": 287335.4,
                      "distance": 3970317
                    }
                  ],
                  "waypoints": [
                    {
                      "hint": "41oZjf___38sAAAAMAAAAAAAAADIAAAAKYgbQsDBWUAAAAAAu7coQywAAAAwAAAAAAAAAMgAAAACjwAAcxc4_TiFmP7IFjj9cIWY_gAAfwwAAAAA",
                      "location": [-46.655629, -23.558856],
                      "name": "Rua Peixoto Gomide",
                      "distance": 18.52527432
                    },
                    {
                      "hint": "KvrGiP___38uAAAALgAAACsBAADPAAAA-PykQgAAAADZtgdEWx-6Qy4AAAAuAAAAKwEAAM8AAAACjwAAO5r0_CeL___gyfT8dHj__wwA_wEAAAAA",
                      "location": [-51.078597, -0.029913],
                      "name": "Rodovia Josmar Chaves Pinto",
                      "distance": 1457.29233
                    }
                  ]
                }
                """;

        stubFor(get(urlPathEqualTo("/route/v1/driving/" + query))
                .willReturn(okJson(json)));

        OsrmDistanceDTO response = osrmService.getDistance(-46.6558,
                                                            -23.5588,
                                                            -51.0664,
                                                            -0.0347);

        assertNotNull(response);
        assertEquals(response.routes().getFirst().distance(), 3970317);
        assertEquals(response.routes().getFirst().duration(), 287335.4);

    }
}