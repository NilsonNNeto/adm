package br.com.seat.distributor.stepdefs;

import br.com.meeting.adm.SeatDistributor;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import io.cucumber.java.DataTableType;
import io.cucumber.java.pt.Dadas;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SeatDistributorStepdefs {

    private List<ParticipationFormEntity> forms = new LinkedList<>();

    private List<String> resultIdsActual = new LinkedList<>();

    @Dadas("as fichas cadastrais")
    public void asFichasCadastrais(List<ParticipationFormEntity> entry) {
        forms.addAll(entry);
    }

    @Quando("ocorrer o processamento dos dados")
    public void ocorrerOProcessamentoDosDados() {
        resultIdsActual = new SeatDistributor().distributeSeats(forms);
    }

    @Entao("a ordem das fichas deverá ser")
    public void aOrdemDasFichasDeveráSer(List<String> idsExpected) {
        Assertions.assertLinesMatch(idsExpected, resultIdsActual);
    }

    @DataTableType
    public ParticipationFormEntity convertParticipationFormEntity(Map<String, String> entry) {
        String capela = entry.get("capela") == null ? "" : entry.get("capela");
        String paroquia = entry.get("paroquia") == null ? "" : entry.get("paroquia");

        return ParticipationFormEntity.builder()
                .id(Integer.parseInt(entry.get("id")))
                .height(new Double(entry.get("altura")))
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
