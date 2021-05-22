package br.com.seat.distributor;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootContext.class)
@CucumberContextConfiguration
public class SpringBootContext {
}
