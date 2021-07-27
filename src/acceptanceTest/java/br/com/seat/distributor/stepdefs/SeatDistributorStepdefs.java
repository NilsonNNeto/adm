package br.com.seat.distributor.stepdefs;

import br.com.meeting.adm.SeatDistributor;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import io.cucumber.java.DataTableType;
import io.cucumber.java.pt.Dadas;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SeatDistributorStepdefs {

    private List<ParticipationFormEntity> forms = new LinkedList<>();

    private List<ParticipationFormEntity> actualResult;

    @Dadas("as fichas cadastrais das cadeiras")
    public void asFichasCadastraisDasCadeiras(List<ParticipationFormEntity> entry) {
        forms.addAll(entry);
    }

    @Quando("ocorrer o processamento dos dados")
    public void ocorrerOProcessamentoDosDados() {
        actualResult = new SeatDistributor().distributeSeats(forms);
    }

    @Entao("a ordem das fichas deverá ser")
    public void aOrdemDasFichasDeveráSer(List<String> expectedResult) {

        assertEquals(expectedResult.size(), actualResult.size());

        expectedResult.forEach(id ->
                {
                    ParticipationFormEntity entity = actualResult.get(expectedResult.indexOf(id));
                    assertEquals(id, entity.getId().toString());
                }
        );

    }

    @DataTableType
    public ParticipationFormEntity convertParticipationFormEntity(Map<String, String> entry) {
        String capela = entry.get("capela") == null ? "" : entry.get("capela");
        String paroquia = entry.get("paroquia") == null ? "" : entry.get("paroquia");
        Double altura = entry.get("altura") == null ? 0 : Double.valueOf(entry.get("altura"));
        Integer idade = entry.get("idade") == null ? 0 : Integer.parseInt(entry.get("idade"));

        return ParticipationFormEntity.builder()
                .id(Integer.parseInt(entry.get("id")))
                .height(altura)
                .age(idade)
                .gender(entry.get("genero"))
                .church(capela.isEmpty() ? paroquia : capela)
                .location(entry.get("bairro"))
                .build();
    }

    @DataTableType
    public String convertIds(Map<String, String> entry) {
        return entry.get("id");
    }

}
