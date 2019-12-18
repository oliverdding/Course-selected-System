package com.oliverdd.util;

import com.oliverdd.model.Schedule;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teachers")
public class SchedulesWrapper {
    private List<Schedule> schedules;
    @XmlElement(name = "teacher")
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
