package br.com.meeting.adm;

import br.com.meeting.adm.entity.ParticipationFormEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AdmApplicationTests {

    @Test
    void testDistributeSeats() {

        List<ParticipationFormEntity> formEntities = new LinkedList<>();
        formEntities.add(buildNewParticipationForm(1, 1.4, "Masculino", "Santo Antônio", "Santa Rosa"));
        formEntities.add(buildNewParticipationForm(2, 1.8, "Feminino", "Matriz", "Astúrias"));
        formEntities.add(buildNewParticipationForm(3, 1.6, "Feminino", "Santa Rosa de Lima", "Santo Antônio"));
        formEntities.add(buildNewParticipationForm(4, 1.4, "Masculino", "", "Santos"));
        formEntities.add(buildNewParticipationForm(5, 1.6, "Feminino", "", "Enseada"));
        formEntities.add(buildNewParticipationForm(6, 1.9, "Feminino", "Santo Antônio", "Astúrias"));
        formEntities.add(buildNewParticipationForm(7, 1.3, "Masculino", "Matriz", "Jardim dos Passáros"));
        formEntities.add(buildNewParticipationForm(8, 2.4, "Feminino", "Santo Antônio", "Jardim dos Passáros"));

        List<ParticipationFormEntity> actualResult = new SeatDistributor().distributeSeats(formEntities);

        List<String> expectedResult = new LinkedList<>();
        expectedResult.add("3");
        expectedResult.add("7");
        expectedResult.add("5");
        expectedResult.add("1");
        expectedResult.add("2");
        expectedResult.add("4");
        expectedResult.add("6");
        expectedResult.add("8");

        for (int index = 0; index < expectedResult.size(); index++) {
            assertEquals(expectedResult.get(index), actualResult.get(index).getId().toString());
        }
    }

    private ParticipationFormEntity buildNewParticipationForm(Integer id, Double height, String gender, String church, String location) {
        return ParticipationFormEntity
                .builder()
                .id(id)
                .height(height)
                .gender(gender)
                .church(church)
                .location(location)
                .build();
    }
}
