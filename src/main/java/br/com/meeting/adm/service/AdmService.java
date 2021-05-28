package br.com.meeting.adm.service;

import br.com.meeting.adm.SeatDistributor;
import br.com.meeting.adm.exception.InvalidFileException;
import br.com.meeting.adm.fileReader.XlsxFileReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdmService {

    public void distributeSeats(final MultipartFile participationForm) throws IOException {

        validateFile(participationForm);

        List<String> result = new SeatDistributor().distributeSeats(new XlsxFileReader().readFileBuildForms(participationForm));

        System.out.println("--------- Ordem ---------");

        result.forEach(x -> System.out.println(x));

        System.out.println("--------- Fim ---------");


    }

    private static void validateFile(final MultipartFile file) {
        if (file == null || !isExtensionsValid(file.getOriginalFilename().toLowerCase())) {
            throw new InvalidFileException("The file is not in XLXS format");
        }
    }

    private static boolean isExtensionsValid(String fileName) {
        //TODO validar formato, não extensao
        return List.of(".xlsx")
                .stream()
                .anyMatch(fileName::endsWith);
    }

}
