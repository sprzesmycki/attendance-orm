package com.pgssoft.service;

import com.pgssoft.exception.BadRequestException;
import com.pgssoft.exception.NotFoundException;
import com.pgssoft.model.Activity;
import com.pgssoft.model.User;
import com.pgssoft.repository.ActivitiesRepository;
import com.pgssoft.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jpiecuch on 2017-02-13.
 */
@Service
public class ActivityService {

    private static final int MAX_ACTIVITIES = 8;
    private ActivitiesRepository activitiesRepository;
    private UsersRepository usersRepository;

    @Autowired
    public ActivityService(ActivitiesRepository activitiesRepository,
                           UsersRepository usersRepository) {
        this.activitiesRepository = activitiesRepository;
        this.usersRepository = usersRepository;
    }

    public List<Activity> get() {
        return activitiesRepository.findAll();
    }

    public Activity create(String name, Date startDate) throws BadRequestException {

        if (activitiesRepository.count() > MAX_ACTIVITIES) {
            throw new BadRequestException("Max activities count reached");
        }

        Activity activity = new Activity();
        activity.setStartDate(startDate);
        activity.setName(name);

        activitiesRepository.save(activity);
        return activity;
    }

    public Activity update(long id, String name, Date startDate) throws BadRequestException, NotFoundException {

        Activity activity = activitiesRepository.findOne(id);

        if (activity == null) {
            throw new NotFoundException("Activity not found");
        }

        activity.setStartDate(startDate);
        activity.setName(name);

        activitiesRepository.save(activity);
        return activity;
    }

    public void delete(Long activityId) throws BadRequestException, NotFoundException {
        activitiesRepository.delete(activityId);
    }
}
