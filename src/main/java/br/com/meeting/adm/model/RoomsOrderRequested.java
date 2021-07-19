package br.com.meeting.adm.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomsOrderRequested {

    private Integer initialRoomFemale;
    private Integer initialRoomMale;
    private String orderFemale;
    private String orderMale;

}
