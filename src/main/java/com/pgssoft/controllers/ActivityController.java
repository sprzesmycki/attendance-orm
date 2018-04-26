package com.pgssoft.controllers;

import com.pgssoft.controllers.converter.Converter;
import com.pgssoft.dto.ActivityDto;
import com.pgssoft.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jpiecuch on 2017-02-13.
 */
@RestController
@RequestMapping(path = "/api/activities")
@CrossOrigin
public class ActivityController {

  private ActivityService activityService;

  @Autowired
  public ActivityController(ActivityService activityService) {
    this.activityService = activityService;
  }

  @PostMapping(path = "/")
  public Long create(@RequestBody @Valid ActivityDto activity) throws Exception {
    return activityService.create(activity.getName(), activity.getStartDate()).getId();
  }

  @GetMapping(path = "/")
  public List<ActivityDto> get() {
    return activityService.get().stream().map(Converter::toActivityDto).collect(Collectors.toList());
  }

  @PutMapping(path = "/{id}")
  public void update(@PathVariable(name = "id") long id, @RequestBody @Valid ActivityDto activityDto) throws Exception {
    activityService.update(id, activityDto.getName(), activityDto.getStartDate());
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable(name = "id") long id) throws Exception {
    activityService.delete(id);
  }
}
