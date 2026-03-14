package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import me.lucaspmntl.geofreight.dto.melhorenvio.request.*;
import me.lucaspmntl.geofreight.dto.melhorenvio.response.MelhorEnvioResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WireMockTest(httpPort = 8089)
@ActiveProfiles("test")
class MelhorEnvioServiceTest {

    @Autowired
    MelhorEnvioService melhorEnvioService;

    @Test
    void shouldReturnCorrectJson() {
        String json = """
                [
                  {
                    "id": 1,
                    "name": "PAC",
                    "price": "37.79",
                    "custom_price": "37.79",
                    "discount": "2.09",
                    "currency": "R$",
                    "delivery_time": 9,
                    "delivery_range": {
                      "min": 8,
                      "max": 9
                    },
                    "custom_delivery_time": 9,
                    "custom_delivery_range": {
                      "min": 8,
                      "max": 9
                    },
                    "packages": [
                      {
                        "price": "37.79",
                        "discount": "2.09",
                        "format": "box",
                        "dimensions": {
                          "height": 2,
                          "width": 11,
                          "length": 16
                        },
                        "weight": "0.10",
                        "insurance_value": "50.00",
                        "products": [
                          {
                            "id": "pequeno",
                            "quantity": 1
                          }
                        ]
                      }
                    ],
                    "additional_services": {
                      "receipt": true,
                      "own_hand": true,
                      "collect": false
                    },
                    "company": {
                      "id": 1,
                      "name": "Correios",
                      "picture": "https://sandbox.melhorenvio.com.br/images/shipping-companies/correios.png"
                    }
                  },
                  {
                    "id": 2,
                    "name": "SEDEX",
                    "price": "46.23",
                    "custom_price": "46.23",
                    "discount": "3.95",
                    "currency": "R$",
                    "delivery_time": 4,
                    "delivery_range": {
                      "min": 3,
                      "max": 4
                    },
                    "custom_delivery_time": 4,
                    "custom_delivery_range": {
                      "min": 3,
                      "max": 4
                    },
                    "packages": [
                      {
                        "price": "46.23",
                        "discount": "3.95",
                        "format": "box",
                        "dimensions": {
                          "height": 2,
                          "width": 11,
                          "length": 16
                        },
                        "weight": "0.10",
                        "insurance_value": "50.00",
                        "products": [
                          {
                            "id": "pequeno",
                            "quantity": 1
                          }
                        ]
                      }
                    ],
                    "additional_services": {
                      "receipt": true,
                      "own_hand": true,
                      "collect": false
                    },
                    "company": {
                      "id": 1,
                      "name": "Correios",
                      "picture": "https://sandbox.melhorenvio.com.br/images/shipping-companies/correios.png"
                    }
                  },
                  {
                    "id": 3,
                    "name": ".Package",
                    "price": "18.60",
                    "custom_price": "18.60",
                    "discount": "4.16",
                    "currency": "R$",
                    "delivery_time": 6,
                    "delivery_range": {
                      "min": 5,
                      "max": 6
                    },
                    "custom_delivery_time": 6,
                    "custom_delivery_range": {
                      "min": 5,
                      "max": 6
                    },
                    "packages": [
                      {
                        "format": "box",
                        "dimensions": {
                          "height": 1,
                          "width": 1,
                          "length": 1
                        },
                        "weight": "0.10",
                        "insurance_value": "50.00",
                        "products": [
                          {
                            "id": "pequeno",
                            "quantity": 1
                          }
                        ]
                      }
                    ],
                    "additional_services": {
                      "receipt": true,
                      "own_hand": false,
                      "collect": false
                    },
                    "company": {
                      "id": 2,
                      "name": "Jadlog",
                      "picture": "https://sandbox.melhorenvio.com.br/images/shipping-companies/jadlog.png"
                    }
                  },
                  {
                    "id": 4,
                    "name": ".Com",
                    "price": "16.44",
                    "custom_price": "16.44",
                    "discount": "0.00",
                    "currency": "R$",
                    "delivery_time": 5,
                    "delivery_range": {
                      "min": 4,
                      "max": 5
                    },
                    "custom_delivery_time": 5,
                    "custom_delivery_range": {
                      "min": 4,
                      "max": 5
                    },
                    "packages": [
                      {
                        "format": "box",
                        "dimensions": {
                          "height": 1,
                          "width": 1,
                          "length": 1
                        },
                        "weight": "0.10",
                        "insurance_value": "50.00",
                        "products": [
                          {
                            "id": "pequeno",
                            "quantity": 1
                          }
                        ]
                      }
                    ],
                    "additional_services": {
                      "receipt": true,
                      "own_hand": false,
                      "collect": false
                    },
                    "company": {
                      "id": 2,
                      "name": "Jadlog",
                      "picture": "https://sandbox.melhorenvio.com.br/images/shipping-companies/jadlog.png"
                    }
                  },
                  {
                    "id": 17,
                    "name": "Mini Envios",
                    "price": "23.44",
                    "custom_price": "23.44",
                    "discount": "0.00",
                    "currency": "R$",
                    "delivery_time": 11,
                    "delivery_range": {
                      "min": 10,
                      "max": 11
                    },
                    "custom_delivery_time": 11,
                    "custom_delivery_range": {
                      "min": 10,
                      "max": 11
                    },
                    "packages": [
                      {
                        "price": "23.44",
                        "discount": "0.00",
                        "format": "box",
                        "dimensions": {
                          "height": 1,
                          "width": 11,
                          "length": 16
                        },
                        "weight": "0.10",
                        "insurance_value": "50.00",
                        "products": [
                          {
                            "id": "pequeno",
                            "quantity": 1
                          }
                        ]
                      }
                    ],
                    "additional_services": {
                      "receipt": true,
                      "own_hand": false,
                      "collect": false
                    },
                    "company": {
                      "id": 1,
                      "name": "Correios",
                      "picture": "https://sandbox.melhorenvio.com.br/images/shipping-companies/correios.png"
                    }
                  }
                ]
                """;

        MelhorEnvioRequestDTO request = new MelhorEnvioRequestDTO(
                new From("68900238"),
                new To("57839029"),
                List.of(new Product("5", 20, 30, 40, 50, 15, 1)),
                new Options(true, false),
                "Nothing");

        stubFor(post(urlPathEqualTo("/api/v2/me/shipment/calculate"))
                .willReturn(okJson(json)));

        List<MelhorEnvioResponseDTO> response = melhorEnvioService.getFreights(request);

        assertNotNull(response);
        assertEquals(5, response.size());

        assertEquals(37.79, response.get(0).price());
        assertEquals("PAC", response.get(0).name());
        assertEquals("Correios", response.get(0).company().name());
        assertEquals(9, response.get(0).deliveryTime());

        assertEquals(46.23, response.get(1).price());
        assertEquals("SEDEX", response.get(1).name());
        assertEquals("Correios", response.get(1).company().name());
        assertEquals(4, response.get(1).deliveryTime());
    }
}