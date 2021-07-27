package br.com.meeting.adm.gateway.controller;

import br.com.meeting.adm.model.RoomsOrderRequest;
import br.com.meeting.adm.service.AdmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("distributor")
public class AdmController {

    private final AdmService admService;

    @ApiOperation(
            value = "Distribuidor de cadeiras",
            notes = "Recebe a planilha das fichas (xls ou xlsx) e cria um arquivo CSV reorganizando as fichas seguindo a ordenação:\n" +
                    "\n- Tamanho (do menor para maior);" +
                    "\n- Gênero (intercala iniciando pelo de maior quantidade);" +
                    "\n- Paróquia/Capela (intercala pessoas da mesma igreja);" +
                    "\n- Cidade/Bairro (intercala pessoas que moram próximas)."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Sucesso, clique acima para fazer o download.", response = ResponseEntity.class),
                    @ApiResponse(code = 500, message = "O arquivo não está no formato XLS ou XLSX, por favor adicione um arquivo válido.")
            }
    )
    @PostMapping(path = "/seats")
    public ResponseEntity distributeSeats(@RequestPart("Fichas") MultipartFile participationForm) throws IOException {

        File file = admService.distributeSeats(participationForm);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));
    }

    @ApiOperation(
            value = "Distribuidor de quartos",
            notes = "Recebe a planilha das fichas (xls ou xlsx) juntamente com a numeração dos quartos e cria um arquivo CSV reorganizando as fichas seguindo a regra:\n" +
                    "\n- Máximo de 3 pessoas por quarto;" +
                    "\n- Gênero (somente mesmo gênero no mesmo quarto);" +
                    "\n- Idade (seleciona uma pessoa de cada faixa etária [0-18] [19-24] [25+]);" +
                    "\n- Paróquia/Capela (evita pessoas da mesma igreja);" +
                    "\n- Cidade/Bairro (evita pessoas que moram próximas)."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Sucesso, clique acima para fazer o download.", response = ResponseEntity.class),
                    @ApiResponse(code = 500, message = "O arquivo não está no formato XLS ou XLSX, por favor adicione um arquivo válido.")
            }
    )
    @PostMapping(path = "/rooms")
    public ResponseEntity distributeRooms(
            @RequestPart(value = "Fichas")
            @Valid MultipartFile participationForm,

            @ApiParam(name = "initialRoomFemale",
                    value = "Quarto inicial - Feminino")
            @RequestParam(name = "initialRoomFemale", required = true, defaultValue = "54")
            @Valid @Min(value = 0, message = "Quarto precisa ser um número maior que 0") final Integer initialRoomFemale,

            @ApiParam(name = "orderFemale",
                    value = "Ordenação - Feminino (C para Crescente ou D para Decrescente)")
            @RequestParam(name = "orderFemale", required = true, defaultValue = "C")
            @Pattern(message = "Ordem precisa ser C ou D", regexp = "^[c]$|^[C]$|^[d]$|^[D]$", flags = Pattern.Flag.CASE_INSENSITIVE) final String orderFemale,

            @ApiParam(name = "initialRoomMale",
                    value = "Quarto inicial - Masculino")
            @RequestParam(name = "initialRoomMale", required = true, defaultValue = "96")
            @Valid @Min(value = 0, message = "Quarto precisa ser um número maior que 0") final Integer initialRoomMale,

            @ApiParam(name = "orderMale",
                    value = "Ordenação - Masculino (C para Crescente ou D para Decrescente)")

            @RequestParam(name = "orderMale", required = true, defaultValue = "D")
            @Pattern(message = "Ordem precisa ser C ou D", regexp = "^[c]$|^[C]$|^[d]$|^[D]$", flags = Pattern.Flag.CASE_INSENSITIVE) final String orderMale
    ) throws IOException {
        RoomsOrderRequest roomsOrderRequeste = RoomsOrderRequest.builder()
                .initialRoomFemale(initialRoomFemale)
                .orderFemale(orderFemale)
                .initialRoomMale(initialRoomMale)
                .orderMale(orderMale)
                .build();

        File file = admService.distributeRooms(participationForm, roomsOrderRequeste);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));
    }

}
