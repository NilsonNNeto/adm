package br.com.meeting.adm.service;

import br.com.meeting.adm.SeatDistributor;
import br.com.meeting.adm.fileReader.XlsxFileReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdmService {

    public void distributeSeats(final MultipartFile participationForm) throws IOException {

        List<String> result = new SeatDistributor().distributeSeats(new XlsxFileReader().readFileBuildForms());

        System.out.println("--------- Ordem ---------");

        result.forEach(x -> System.out.println(x));

        System.out.println("--------- Fim ---------");


    }

}
