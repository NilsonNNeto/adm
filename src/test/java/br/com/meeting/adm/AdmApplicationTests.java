package br.com.meeting.adm;

import br.com.meeting.adm.entity.ParticipationFormEntity;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class AdmApplicationTests {

    //	@Test
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

        List<String> resultActual = new SeatDistributor().distributeSeats(formEntities);

        List<String> resultExpected = new LinkedList<>();
        resultExpected.add("3");
        resultExpected.add("7");
        resultExpected.add("5");
        resultExpected.add("1");
        resultExpected.add("2");
        resultExpected.add("4");
        resultExpected.add("6");
        resultExpected.add("8");

        Assertions.assertLinesMatch(resultExpected, resultActual);

    }

    //	@Test
    void testDistributeSeatsForEmpty() {

        List<String> resultActual = new SeatDistributor().distributeSeats(new LinkedList<>());

        Assertions.assertLinesMatch(Collections.emptyList(), resultActual);

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
