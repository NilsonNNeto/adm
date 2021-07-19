package br.com.meeting.adm;

import br.com.meeting.adm.entity.GenderEnum;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import br.com.meeting.adm.model.RoomsOrderRequested;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDistributor {

    private List<ParticipationFormEntity> underNineteenList;
    private List<ParticipationFormEntity> nineteenToTwentyFourList;
    private List<ParticipationFormEntity> aboveTwentyFourList;
    private List<ParticipationFormEntity> resultList = new LinkedList<>();

    public List<ParticipationFormEntity> distributeRooms(List<ParticipationFormEntity> formEntities, RoomsOrderRequested roomsOrderRequested) {
        Arrays.stream(GenderEnum.values()).forEach(gender -> {
            distributeByGender(formEntities, roomsOrderRequested, gender);
        });

        return resultList;
    }

    private void distributeByGender(List<ParticipationFormEntity> formEntities, RoomsOrderRequested roomsOrder, GenderEnum gender) {

        defineListsByAge(formEntities, gender);

        Integer room = 0;
        List<ParticipationFormEntity> roommates;

        while (haveParticipantsToDistribute()) {

            room = getNewRoom(room, roomsOrder, gender);
            roommates = new LinkedList<>();

            checkNewRoommate(room, roommates, underNineteenList);

            checkNewRoommate(room, roommates, nineteenToTwentyFourList);

            checkNewRoommate(room, roommates, aboveTwentyFourList);

            resultList.addAll(roommates);
        }
    }

    private void defineListsByAge(List<ParticipationFormEntity> formEntities, GenderEnum gender) {
        List<ParticipationFormEntity> participants =
                getParticipantsByGender(formEntities, gender);

        underNineteenList = participants
                .stream()
                .filter(entity -> entity.getAge() < 19)
                .collect(Collectors.toList());

        nineteenToTwentyFourList = participants
                .stream()
                .filter(entity -> entity.getAge() >= 19 && entity.getAge() <= 24)
                .collect(Collectors.toList());

        aboveTwentyFourList = participants
                .stream()
                .filter(entity -> entity.getAge() > 24)
                .collect(Collectors.toList());
    }

    private List<ParticipationFormEntity> getParticipantsByGender(List<ParticipationFormEntity> formEntities, GenderEnum gender) {
        return formEntities
                .stream()
                .filter(entity -> gender.name().equalsIgnoreCase(entity.getGender()))
                .collect(Collectors.toList());
    }

    private boolean haveParticipantsToDistribute() {
        return !underNineteenList.isEmpty() || !nineteenToTwentyFourList.isEmpty() || !aboveTwentyFourList.isEmpty();
    }

    private Integer getNewRoom(Integer room, RoomsOrderRequested roomsOrder, GenderEnum gender) {

        if (room == 0 && GenderEnum.FEMININO == gender) {
            return roomsOrder.getInitialRoomFemale() < 0 ? 0 : roomsOrder.getInitialRoomFemale();
        }

        if (room == 0 && GenderEnum.MASCULINO == gender) {
            return roomsOrder.getInitialRoomMale() < 0 ? 0 : roomsOrder.getInitialRoomMale();
        }

        if (GenderEnum.FEMININO == gender) {
            if (roomsOrder.getOrderFemale() == "C") {
                return ++room;
            } else {
                return --room;
            }
        }

        if (GenderEnum.MASCULINO == gender) {
            if (roomsOrder.getOrderMale() == "C") {
                return ++room;
            } else {
                return --room;
            }
        }

        return 0;
    }

    private void checkNewRoommate(Integer room, List<ParticipationFormEntity> roommates, List<ParticipationFormEntity> entities) {

        for (ParticipationFormEntity newRoommate : entities) {
            boolean acceptNewRoommate = true;

            for (ParticipationFormEntity mate : roommates) {
                acceptNewRoommate = acceptNewRoommate && isDifferentChurchAndLocation(mate, newRoommate);
            }

            if (acceptNewRoommate || isTheLastOfList(entities, newRoommate)) {
                entities.remove(newRoommate);
                newRoommate.setRoom(room);
                roommates.add(newRoommate);
                return;
            }

        }

    }

    private boolean isDifferentChurchAndLocation(ParticipationFormEntity roommate, ParticipationFormEntity
            newRoommate) {
        boolean differentChurch = !roommate.getChurch().trim().equalsIgnoreCase(newRoommate.getChurch().trim());
        boolean differentLocation = !roommate.getLocation().trim().equalsIgnoreCase(newRoommate.getLocation().trim());

        return differentChurch && differentLocation;
    }

    private boolean isTheLastOfList(List<ParticipationFormEntity> entities, ParticipationFormEntity entity) {
        return (entities.size() - 1) == entities.indexOf(entity);
    }

}
