package com.pgssoft.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jpiecuch on 2017-02-15.
 */
public class UserActivityDto implements Serializable {

    private Long id;
    private Long activityId;
    private Boolean present;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
