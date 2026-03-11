package me.lucaspmntl.geofreight.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest(httpPort = 8089)
class ViaCepServiceTest {

    @Test
    void MustReturnCorrectAddress() {
        stubFor(get())
    }
}

