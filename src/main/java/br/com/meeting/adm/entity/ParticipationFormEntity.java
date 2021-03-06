package br.com.meeting.adm.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipationFormEntity implements Comparable<ParticipationFormEntity> {

    private Integer id;
    private Double height;
    private String gender;
    private Integer age;
    private String church;
    private String location;
    private Integer room;

    @Override
    public int compareTo(ParticipationFormEntity other) {
        return this.height.compareTo(other.getHeight());
    }
}