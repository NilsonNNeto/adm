package br.com.meeting.adm.service;

import br.com.meeting.adm.RoomDistributor;
import br.com.meeting.adm.SeatDistributor;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import br.com.meeting.adm.exception.InvalidFileException;
import br.com.meeting.adm.fileReader.XlsxFileReader;
import br.com.meeting.adm.model.RoomsOrderRequest;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class AdmService {

    private final String CSV_FILE_NAME = "Fichas distribuídas na cadeiras.csv";
    private final String CSV_ROOMS_FILE_NAME = "Fichas distribuídas por quartos.csv";

    public File distributeSeats(final MultipartFile participationForm) throws IOException {
        validateFile(participationForm);

        List<ParticipationFormEntity> formParticipants =
                new XlsxFileReader().readFileBuildForms(participationForm);


        List<ParticipationFormEntity> distributedParticipants = new SeatDistributor().distributeSeats(formParticipants);
        return createSeatsResultFile(distributedParticipants);
    }

    public File distributeRooms(final MultipartFile participationForm, RoomsOrderRequest roomsOrderRequeste) throws IOException {
        validateFile(participationForm);

        List<ParticipationFormEntity> formParticipants =
                new XlsxFileReader().readFileBuildForms(participationForm);

        List<ParticipationFormEntity> distributedParticipants =
                new RoomDistributor().distributeRooms(formParticipants, roomsOrderRequeste);

        return createRoomsResultFile(distributedParticipants);
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

    @SneakyThrows
    private File createRoomsResultFile(List<ParticipationFormEntity> resultList) {
        FileWriter csvFile = new FileWriter(CSV_ROOMS_FILE_NAME);

        csvFile.append("|Ficha|Quarto|\n");

        resultList.stream()
                .forEachOrdered(entity -> {
                            try {
                                csvFile.append("|" + entity.getId() + "|")
                                        .append(entity.getRoom() + "|")
                                        .append("\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );

        csvFile.flush();
        csvFile.close();

        return new File(CSV_ROOMS_FILE_NAME);
    }

    @SneakyThrows
    private File createSeatsResultFile(List<ParticipationFormEntity> resultList) {
        FileWriter csvFile = new FileWriter(CSV_FILE_NAME);

        csvFile.append("|Ficha|Cadeira|\n");

        resultList.stream()
                .forEachOrdered(entity -> {
                            try {
                                csvFile.append("|" + entity.getId() + "|")
                                        .append(resultList.indexOf(entity) + 1 + "|")
                                        .append("\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );

        csvFile.flush();
        csvFile.close();

        return new File(CSV_FILE_NAME);
    }

}
