package com.pgssoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pgssoft.exception.BadRequestException;
import com.pgssoft.exception.NotFoundException;
import com.pgssoft.model.Activity;
import com.pgssoft.model.User;
import com.pgssoft.model.UserActivity;
import com.pgssoft.repository.ActivitiesRepository;
import com.pgssoft.repository.UserActivityRepository;
import com.pgssoft.repository.UsersRepository;

/**
 * Created by jpiecuch on 2017-02-13.
 */
@Service
public class UserService {

    private UsersRepository repository;
    private ActivitiesRepository activitiesRepository;
    private UserActivityRepository userActivityRepository;

    @Autowired
    public UserService(UsersRepository repository, ActivitiesRepository activitiesRepository, UserActivityRepository userActivityRepository) {
        this.repository = repository;
        this.activitiesRepository = activitiesRepository;
        this.userActivityRepository = userActivityRepository;
    }

    public Long createuser(User user) {
        user.setRole(User.Role.STUDENT);
        return repository.save(user).getId();
    }

    public User unique(long id) {
        return repository.findOne(id);
    }

    public List<User> getStudents() {
        return repository.findByRole(User.Role.STUDENT);
    }

    public void update(long userId, String firstName, String lastName, String email) {
        final User user = repository.findOne(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        repository.save(user);
    }

    public void deleteStudent(Long userId) throws BadRequestException, NotFoundException {
        User user = repository.findOne(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        // we can remove only student
        if (user.getRole() != User.Role.STUDENT) {
            throw new BadRequestException("Only student can be deleted!");
        }

        repository.delete(userId);
    }

    public Long addUserActivity(Long userId, Long activityId, Boolean present) throws NotFoundException {
        User user = repository.findOne(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Activity activity = activitiesRepository.findOne(activityId);
        if(activity == null) {
            throw new NotFoundException("Activity not found");
        }

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(activity);
        userActivity.setPresent(present);

        userActivity = userActivityRepository.save(userActivity);

        return userActivity.getId();
    }

    public void setUserActivity(Long userId, Long activityId, Boolean present) throws NotFoundException {
      User user = repository.findOne(userId);
      if (user == null) {
        throw new NotFoundException("User not found");
      }

      Activity activity = activitiesRepository.findOne(activityId);
      if(activity == null) {
        throw new NotFoundException("Activity not found");
      }

      UserActivity userActivity = userActivityRepository.findByUserAndActivity(user, activity)
        .stream().findFirst().orElseThrow(() -> new NotFoundException("Activity not found"));

      userActivity.setPresent(present);
      userActivityRepository.save(userActivity);
    }
}
