package com.pgssoft.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.pgssoft.model.Activity;
import com.pgssoft.model.User;
import com.pgssoft.model.UserActivity;

@Repository
public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {

  List<UserActivity> findByUserAndActivity(User user, Activity activity);
}
