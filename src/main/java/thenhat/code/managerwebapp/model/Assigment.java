package thenhat.code.managerwebapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


public class Assigment {
    public Long getSchedules_id() {
        return schedules_id;
    }

    public void setSchedules_id(Long schedules_id) {
        this.schedules_id = schedules_id;
    }

    public Long getCanbo1() {
        return canbo1;
    }

    public void setCanbo1(Long canbo1) {
        this.canbo1 = canbo1;
    }

    public Long getCanbo2() {
        return canbo2;
    }

    public void setCanbo2(Long canbo2) {
        this.canbo2 = canbo2;
    }

    private Long schedules_id;
    private Long canbo1;
    private Long canbo2;
}
