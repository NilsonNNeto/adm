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

    public RoomsOrderRequested(Integer initialRoomFemale, Integer initialRoomMale, String orderFemale, String orderMale) {
        this.initialRoomFemale = initialRoomFemale;
        this.initialRoomMale = initialRoomMale;
        this.orderFemale = orderFemale;
        this.orderMale = orderMale;
    }
}
