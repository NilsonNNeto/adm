package br.com.meeting.adm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmApplication.class, args);
    }

}
