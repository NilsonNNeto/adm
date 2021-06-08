package br.com.meeting.adm.service;

import br.com.meeting.adm.SeatDistributor;
import br.com.meeting.adm.exception.InvalidFileException;
import br.com.meeting.adm.fileReader.XlsxFileReader;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AdmService {

    public File distributeSeats(final MultipartFile participationForm) throws IOException {

        validateFile(participationForm);

        return new SeatDistributor().buildCsvFromFile(new XlsxFileReader().readFileBuildForms(participationForm));
    }

    private static void validateFile(final MultipartFile file) {
        if (file == null || !isExtensionValid(FilenameUtils.getExtension(file.getOriginalFilename()))) {
            throw new InvalidFileException("O arquivo não está no formato XLS ou XLSX, por favor adicione um arquivo válido.");
        }
    }

    private static boolean isExtensionValid(String fileExtension) {
        return List.of("xls", "xlsx")
                .stream()
                .anyMatch(fileExtension::equalsIgnoreCase);
    }

}
