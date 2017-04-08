package com.pgssoft.IT;

import com.pgssoft.dto.ActivityDto;
import com.pgssoft.dto.UserActivityDto;
import com.pgssoft.dto.UserDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.condition.AllOf.allOf;

public class ManagingActivityIT extends AbstractITTest {

    private static final String ACTIVITIES_URL = "/activities";
    private static final String SINGLE_ACTIVITY_URL = ACTIVITIES_URL + "/%d";
    private static final Long ACTIVITY_ID = 3L;
    private static final Long USER_ID = 3L;

    @Test
    public void managingActivity() {
        ActivityDto expected = createActivity();
        expected.setId(-10L);
        Long savedID = testSave(expected);
        testEdit(expected, savedID);
        testDelete(savedID);
    }

    private void testDelete(Long savedID) {
        restTemplate.delete(String.format(SINGLE_ACTIVITY_URL, savedID));
        ActivityDto[] activities = restTemplate.getForObject(ACTIVITIES_URL, ActivityDto[].class);
        assertThat(activities)
                .isNotNull()
                .areNot(conditionOf(activityDto -> savedID.equals(activityDto.getId())));
    }

    private void testEdit(ActivityDto expected, Long savedID) {
        expected.setName(expected.getName() + "2");
        expected.setStartDate(new Date(10000));
        restTemplate.put(String.format(SINGLE_ACTIVITY_URL, savedID), expected);
        assertCreated(expected, savedID, restTemplate.getForEntity(ACTIVITIES_URL, ActivityDto[].class));
    }

    private Long testSave(ActivityDto expected) {
        ResponseEntity<Long> saveResponse = restTemplate.postForEntity(ACTIVITIES_URL, expected, Long.class);
        assertThat(saveResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Long savedID = saveResponse.getBody();
        assertCreated(expected, savedID, restTemplate.getForEntity(ACTIVITIES_URL, ActivityDto[].class));
        return savedID;
    }

    @Test
    public void addingUserActivity() {
        String url = String.format("/users/%d/activites", USER_ID);
        UserActivityDto dto = new UserActivityDto();
        dto.setActivityId(ACTIVITY_ID);
        dto.setPresent(Boolean.TRUE);

        Long response = restTemplate.postForObject(url, dto, Long.class);
        assertThat(response).isNotNull();

        UserDto[] users = restTemplate.getForObject("/users/?expand=true", UserDto[].class);
        assertThat(users).isNotNull().areExactly(1, allOf(
                conditionOf("User's id matches", user -> USER_ID.equals(user.getId())),
                conditionOf("Activity id matches", user -> user.getActivities().stream().filter(activity -> {
                    return Boolean.TRUE.equals(activity.getPresent()) && activity.getActivityId().equals(ACTIVITY_ID);
                }).count() == 1)
        ));
    }

    private void assertCreated(ActivityDto expected, Long savedID, ResponseEntity<ActivityDto[]> items) {
        assertThat(items.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(items.getBody()).areExactly(1, allOf(
                conditionOf("Id matches", dto -> Objects.equals(savedID, dto.getId())),
                conditionOf("Names are the same", dto -> expected.getName().equals(dto.getName())),
                conditionOf("Start date is the same", dto -> expected.getStartDate().equals(dto.getStartDate()))
        ));
    }

    private ActivityDto createActivity() {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setName("New activity");
        activityDto.setStartDate(new Date());
        return activityDto;
    }
}
