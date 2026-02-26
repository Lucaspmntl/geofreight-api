package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@WireMockTest(httpPort = 8089)
class OsrmServiceTest {

    private OsrmService osrmService;

    @Test
    void getDistance() {
    }
}