package br.com.seat.distributor.stepdefs;

import br.com.meeting.adm.RoomDistributor;
import br.com.meeting.adm.entity.ParticipationFormEntity;
import br.com.meeting.adm.model.RoomsOrderRequest;
import io.cucumber.java.DataTableType;
import io.cucumber.java.pt.Dadas;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RoomDistributorStepDefs {

    private List<ParticipationFormEntity> forms = new LinkedList<>();
    private RoomsOrderRequest roomsOrderRequest;

    private List<ParticipationFormEntity> actualResult;

    @Dadas("as fichas cadastrais dos quartos")
    public void asFichasCadastraisDosQuartos(List<ParticipationFormEntity> entry) {
        forms.addAll(entry);
    }

    @E("o número do quarto feminino e masculino com a ordenação")
    public void oNumeroDoQuartoFemininoEMasculinoComAOrdenacao(RoomsOrderRequest entry) {
        roomsOrderRequest = entry;
    }

    @Quando("ocorrer o separação dos quartos")
    public void ocorrerOSeparacaoDosQuartos() {
        actualResult = new RoomDistributor().distributeRooms(forms, roomsOrderRequest);
    }

    @Entao("a divisão dos quartos deverá ser")
    public void aDivisaoDosQuartosDeveraSer(Map<String, String> expectedResult) {

        assertEquals(expectedResult.size(), actualResult.size());

        expectedResult.forEach((id, room) ->
                actualResult.forEach(entity -> {

                    if (id.equals(entity.getId())) {
                        assertEquals(room, entity.getRoom());
                        return;
                    }

                }));
    }

    @DataTableType
    public RoomsOrderRequest convertRoomsOrderRequest(Map<String, String> entry) {
        return RoomsOrderRequest.builder()
                .initialRoomFemale(Integer.parseInt(entry.get("quarto feminino")))
                .orderFemale(entry.get("ordem feminino"))
                .initialRoomMale(Integer.parseInt(entry.get("quarto masculino")))
                .orderMale(entry.get("ordem masculino"))
                .build();
    }

}
