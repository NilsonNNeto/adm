package br.com.meeting.adm.gateway.controller;

import br.com.meeting.adm.service.AdmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("Distribuidor de cadeiras")
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/csv")
    public ResponseEntity distributeSeats(@RequestPart("Fichas") MultipartFile participationForm) throws IOException {

        File file = admService.distributeSeats(participationForm);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));
    }


}
