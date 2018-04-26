package com.pgssoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

/**
 * Created by jpiecuch on 2017-02-14.
 */
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"user_id", "activity_id"}))
@Entity
public class UserActivity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(updatable = false)
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(updatable = false)
    @NotNull
    private Activity activity;
    private Boolean present;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
