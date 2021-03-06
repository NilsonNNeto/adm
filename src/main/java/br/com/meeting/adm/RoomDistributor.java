package br.com.meeting.adm;

import br.com.meeting.adm.entity.GenderEnum;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import br.com.meeting.adm.model.RoomsOrderRequest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDistributor {

    private List<ParticipationFormEntity> underNineteenList;
    private List<ParticipationFormEntity> nineteenToTwentyFourList;
    private List<ParticipationFormEntity> aboveTwentyFourList;
    private List<ParticipationFormEntity> resultList = new LinkedList<>();

    public List<ParticipationFormEntity> distributeRooms(List<ParticipationFormEntity> formEntities, RoomsOrderRequest roomsOrderRequeste) {
        Arrays.stream(GenderEnum.values()).forEach(gender -> {
            distributeByGender(formEntities, roomsOrderRequeste, gender);
        });

        return resultList;
    }

    private void distributeByGender(List<ParticipationFormEntity> formEntities, RoomsOrderRequest roomsOrder, GenderEnum gender) {

        defineListsByAge(formEntities, gender);

        Integer room = getFirstRoom(roomsOrder, gender);
        List<ParticipationFormEntity> roommates = new LinkedList<>();

        while (haveParticipantsToDistribute()) {

            checkNewRoommate(room, roommates, underNineteenList);

            checkNewRoommate(room, roommates, nineteenToTwentyFourList);

            checkNewRoommate(room, roommates, aboveTwentyFourList);

            if (haveGoodRoommatesNumber(roommates)) {
                resultList.addAll(roommates);
                roommates = new LinkedList<>();
                room = getNewRoom(room, roomsOrder, gender);
            }
        }
    }

    private boolean haveGoodRoommatesNumber(List<ParticipationFormEntity> roommates) {
        return roommates.size() == 3 || !haveParticipantsToDistribute();
    }

    private Integer getFirstRoom(RoomsOrderRequest roomsOrder, GenderEnum gender) {
        Integer newRoom = 0;

        if (GenderEnum.FEMININO == gender) {
            newRoom = roomsOrder.getInitialRoomFemale();
        }

        if (GenderEnum.MASCULINO == gender) {
            newRoom = roomsOrder.getInitialRoomMale();
        }

        return newRoom > 0 ? newRoom : 0;

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

    private Integer getNewRoom(Integer room, RoomsOrderRequest roomsOrder, GenderEnum gender) {

        if (GenderEnum.FEMININO == gender) {
            if ("C".equalsIgnoreCase(roomsOrder.getOrderFemale())) {
                room++;
            } else {
                room--;
            }
        }

        if (GenderEnum.MASCULINO == gender) {
            if ("C".equalsIgnoreCase(roomsOrder.getOrderMale())) {
                room++;
            } else {
                room--;
            }
        }

        return room;
    }

    private void checkNewRoommate(Integer room, List<ParticipationFormEntity> roommates, List<ParticipationFormEntity> entities) {

        if (roommates.size() == 3) return;

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
