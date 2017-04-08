package com.pgssoft.repository;

import com.pgssoft.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jpiecuch on 2017-02-13.
 */
public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
}
