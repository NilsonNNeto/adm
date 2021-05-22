package br.com.meeting.adm;

import br.com.meeting.adm.fileReader.XlsxFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AdmApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(AdmApplication.class, args);
//    }

    public static void main(String[] args) {

        try {
            List<String> result = new SeatDistributor().distributeSeats(new XlsxFileReader().readFileBuildForms());

            System.out.println("--------- Ordem ---------");

            result.forEach(x -> System.out.println(x));

            System.out.println("--------- Fim ---------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
