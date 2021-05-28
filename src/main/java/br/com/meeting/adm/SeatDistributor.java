package br.com.meeting.adm;

import br.com.meeting.adm.entity.ParticipationFormEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatDistributor {

    private final String MASCULINO = "Masculino";
    private final String FEMININO = "Feminino";

    private List<ParticipationFormEntity> resultList = new LinkedList<>();
    private List<ParticipationFormEntity> firstList;
    private List<ParticipationFormEntity> secondList;

    public List<String> distributeSeats(List<ParticipationFormEntity> formEntities) {

        List<ParticipationFormEntity> entitiesOrderedByHeight = formEntities.stream().sorted().collect(Collectors.toList());

        defineFirstAndSecondLists(entitiesOrderedByHeight);

        resultList.add(firstList.get(0));
        firstList.remove(0);

        entitiesOrderedByHeight.forEach(x -> {
            getNextFromAllEntities(secondList);
            getNextFromAllEntities(firstList);
        });

        return transformResult(resultList);
    }

    private void getNextFromAllEntities(List<ParticipationFormEntity> workingList) {

        ParticipationFormEntity lastEntity = resultList.get(resultList.size() - 1);

        for (ParticipationFormEntity entity : workingList) {
            if (!isSameString(lastEntity.getChurch(), entity.getChurch())) {
                workingList.remove(entity);
                resultList.add(entity);
                return;
            }
        }

        for (ParticipationFormEntity entity : workingList) {
            if (!isSameString(lastEntity.getLocation(), entity.getLocation())) {
                workingList.remove(entity);
                resultList.add(entity);
                return;
            }
        }

        for (ParticipationFormEntity entity : workingList) {
            workingList.remove(entity);
            resultList.add(entity);
            return;
        }

    }

    private void defineFirstAndSecondLists(List<ParticipationFormEntity> formEntities) {
        List<ParticipationFormEntity> formsMascOnly = formEntities.stream().filter(entity -> MASCULINO.equalsIgnoreCase(entity.getGender())).collect(Collectors.toList());
        List<ParticipationFormEntity> formsFemOnly = formEntities.stream().filter(entity -> FEMININO.equalsIgnoreCase(entity.getGender())).collect(Collectors.toList());

        firstList = formsMascOnly.size() > formsFemOnly.size() ? new LinkedList<>(formsMascOnly) : new LinkedList<>(formsFemOnly);
        secondList = formsMascOnly.size() <= formsFemOnly.size() ? new LinkedList<>(formsMascOnly) : new LinkedList<>(formsFemOnly);
    }

    private List<String> transformResult(List<ParticipationFormEntity> entitiesResultList) {
        List<String> result = new LinkedList<>();
        entitiesResultList.forEach(entity -> result.add(entity.getId().toString()));

        return result;
    }

    private boolean isSameString(String string1, String string2) {
        return string1.trim().equalsIgnoreCase(string2.trim());
    }

}
