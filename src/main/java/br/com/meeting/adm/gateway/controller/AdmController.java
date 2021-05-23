package br.com.meeting.adm.gateway.controller;

import br.com.meeting.adm.service.AdmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("meeting-adm")
public class AdmController {

    private final AdmService admService;

    @ApiOperation(
            value = "Distribute Seats",
            notes = "Create an ordered csv from a xlsx file"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 422, message = "Invalid file")
            }
    )
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MultipartFile distributeSeats(@RequestPart("participationForm") MultipartFile participationForm) throws IOException {
        admService.distributeSeats(participationForm);

        return null;
    }


}
