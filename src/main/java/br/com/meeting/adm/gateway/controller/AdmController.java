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
            notes = "Recebe a planilha das fichas (xls ou xlsx) e cria um arquivo CSV reorganizando as fichas conforme a seguinte ordenação:\n" +
                    "\n- Altura (do menor para maior);" +
                    "\n- Gênero (intercala iniciando pelo gênero de maior quantidade);" +
                    "\n- Paróquia/Capela (intercala pessoas de igreja/capela diferentes);" +
                    "\n- Cidade/Bairro (intercala pessoas que moram em cidades/bairros diferentes)."+
                    "\n\n\n O arquivo a ser enviado deve conter as seguintes colunas nas posições: \n" +
                    "\n- Coluna B : Número da Ficha" +
                    "\n- Coluna E : Altura" +
                    "\n- Coluna F : Gênero" +
                    "\n- Coluna H : Idade" +
                    "\n- Coluna K : Bairro" +
                    "\n- Coluna P : Paróquia" +
                    "\n- Coluna Q : Capela"
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
            notes = "Recebe a planilha das fichas (xls ou xlsx) juntamente com o início da numeração dos quartos e cria um arquivo CSV reorganizando as fichas conforme a seguinte regra:\n" +
                    "\n- Máximo de 3 pessoas por quarto;" +
                    "\n- Gênero (somente mesmo gênero no mesmo quarto);" +
                    "\n- Idade (seleciona uma pessoa de cada faixa etária abaixo);" +
                    "\n  - de 0 até 18 anos" +
                    "\n  - de 19 até 24 anos" +
                    "\n  - maiores de 25 anos" +
                    "\n- Paróquia/Capela (evita pessoas da mesma igreja/capela);" +
                    "\n- Cidade/Bairro (evita pessoas que moram na mesma cidade/bairro)."+
                    "\n\n\n O arquivo a ser enviado deve conter as seguintes colunas nas posições: \n" +
                    "\n- Coluna B : Número da Ficha" +
                    "\n- Coluna E : Altura" +
                    "\n- Coluna F : Gênero" +
                    "\n- Coluna H : Idade" +
                    "\n- Coluna K : Bairro" +
                    "\n- Coluna P : Paróquia" +
                    "\n- Coluna Q : Capela"
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
                    value = "Número do quarto inicial (Feminino)")
            @RequestParam(name = "initialRoomFemale", required = true, defaultValue = "54")
            @Valid @Min(value = 0, message = "Quarto precisa ser um número maior que 0") final Integer initialRoomFemale,

            @ApiParam(name = "orderFemale",
                    value = "Ordenação dos quartos Feminino, sendo C = Ordem Crescente ou D = Ordem Decrescente.")
            @RequestParam(name = "orderFemale", required = true, defaultValue = "C")
            @Pattern(message = "Ordem precisa ser C ou D", regexp = "^[c]$|^[C]$|^[d]$|^[D]$", flags = Pattern.Flag.CASE_INSENSITIVE) final String orderFemale,

            @ApiParam(name = "initialRoomMale",
                    value = "Número do quarto inicial (Masculino)")
            @RequestParam(name = "initialRoomMale", required = true, defaultValue = "96")
            @Valid @Min(value = 0, message = "Quarto precisa ser um número maior que 0") final Integer initialRoomMale,

            @ApiParam(name = "orderMale",
                    value = "Ordenação dos quartos Masculino, sendo C = Ordem Crescente ou D = Ordem Decrescente.")
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
