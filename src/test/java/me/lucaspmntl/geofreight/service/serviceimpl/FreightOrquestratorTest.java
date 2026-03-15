package me.lucaspmntl.geofreight.service.serviceimpl;

import me.lucaspmntl.geofreight.service.MelhorEnvioService;
import me.lucaspmntl.geofreight.service.ViaCepService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FreightOrquestratorTest {

    @Mock
    ViaCepService viaCepService;

    @Mock
    MelhorEnvioService melhorEnvioService;

    @InjectMocks
    FreightOrquestrator orquestrator;


}
