package br.com.meeting.adm;

import br.com.meeting.adm.entity.Gender;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDistributor {

    private final String CSV_FILE_NAME = "Fichas distribuídas por quartos.csv";

    private List<ParticipationFormEntity> underNineteenList;
    private List<ParticipationFormEntity> nineteenToTwentyfourList;
    private List<ParticipationFormEntity> aboveTwentyFourList;
    private List<ParticipationFormEntity> resultList = new LinkedList<>();

    public File distributeRooms(List<ParticipationFormEntity> formEntities) {
        Arrays.stream(Gender.values()).forEach(gender -> {
            distributeByGender(formEntities, gender);
        });

        return createResultFile();
    }

    private void distributeByGender(List<ParticipationFormEntity> formEntities, Gender gender) {

        defineListsByAge(formEntities, Gender.FEMININO);

        while (haveParticipantsToDistribute()) {

            Integer room = 0;
            List<ParticipationFormEntity> roommates = new LinkedList<>();

            if (underNineteenList.size() > 0) {
                checkNewRoommate(room, roommates, underNineteenList);
            }

            if (nineteenToTwentyfourList.size() > 0) {
                checkNewRoommate(room, roommates, nineteenToTwentyfourList);
            }

            if (aboveTwentyFourList.size() > 0) {
                checkNewRoommate(room, roommates, aboveTwentyFourList);
            }

            resultList.addAll(roommates);
        }
    }

    private void checkNewRoommate(Integer room, List<ParticipationFormEntity> roommates, List<ParticipationFormEntity> entities) {
        ParticipationFormEntity newRoommate = entities.get(0);
        boolean acceptNewRoommate = true;

        for (ParticipationFormEntity mate : roommates) {
            acceptNewRoommate = acceptNewRoommate && isDifferentChurchAndLocation(mate, newRoommate);
        }

        if (acceptNewRoommate) {
            entities.remove(newRoommate);
            newRoommate.setRoom(room);
            roommates.add(newRoommate);
        }

    }

    private boolean isDifferentChurchAndLocation(ParticipationFormEntity roommate, ParticipationFormEntity newRoommate) {
        boolean differentChurch = !roommate.getChurch().trim().equalsIgnoreCase(newRoommate.getChurch().trim());
        boolean differentLocation = !roommate.getLocation().trim().equalsIgnoreCase(newRoommate.getLocation().trim());

        return differentChurch && differentLocation;
    }

    private void defineListsByAge(List<ParticipationFormEntity> formEntities, Gender gender) {
        List<ParticipationFormEntity> participants =
                getParticipantsByGender(formEntities, gender.name());

        underNineteenList = participants
                .stream()
                .filter(entity -> entity.getAge() < 19)
                .collect(Collectors.toList());

        nineteenToTwentyfourList = participants
                .stream()
                .filter(entity -> entity.getAge() >= 19 && entity.getAge() <= 24)
                .collect(Collectors.toList());

        aboveTwentyFourList = participants
                .stream()
                .filter(entity -> entity.getAge() > 24)
                .collect(Collectors.toList());
    }

    private List<ParticipationFormEntity> getParticipantsByGender(List<ParticipationFormEntity> formEntities, String gender) {
        return formEntities
                .stream()
                .filter(entity -> gender.equalsIgnoreCase(entity.getGender()))
                .collect(Collectors.toList());
    }

    private boolean haveParticipantsToDistribute() {
        return !underNineteenList.isEmpty() || !nineteenToTwentyfourList.isEmpty() || !aboveTwentyFourList.isEmpty();
    }

    @SneakyThrows
    private File createResultFile() {
        FileWriter csvFile = new FileWriter(CSV_FILE_NAME);

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

        return new File(CSV_FILE_NAME);
    }
}
