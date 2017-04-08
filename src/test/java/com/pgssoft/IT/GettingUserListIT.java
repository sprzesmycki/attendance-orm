package com.pgssoft.IT;

import com.pgssoft.dto.UserActivityDto;
import com.pgssoft.dto.UserDto;
import com.pgssoft.model.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GettingUserListIT extends AbstractITTest {

    @Test
    public void testGetUsersNoExpand() {
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity("/users", UserDto[].class);
        assertUsersResponse(response);
        assertThat(response.getBody()).extracting(UserDto::getActivities).are(conditionOf(CollectionUtils::isEmpty));
    }

    @Test
    public void testGetUsersExpand() {
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity("/users?expand=true", UserDto[].class);
        assertUsersResponse(response);
        UserDto[] users = response.getBody();

        assertThat(users).extracting(UserDto::getActivities).hasSize(2);
        assertThat(users).extracting(UserDto::getActivities)
                .areNot(conditionOf(CollectionUtils::isEmpty));

        Stream.of(users).collect(ArrayList<UserActivityDto>::new, (list, user) -> list.addAll(user.getActivities()), List::addAll)
                .forEach(activity -> {
                    assertThat(activity.getId()).isNotNull();
                    assertThat(activity.getActivityId()).isNotNull();
                    assertThat(activity.getPresent()).isNotNull();
                });

    }

    private void assertUsersResponse(ResponseEntity<UserDto[]> response) {
        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isNotNull().hasSize(2);
        assertThat(response.getBody()).extracting(UserDto::getRole).containsOnly(User.Role.STUDENT.name());
        assertThat(response.getBody()).extracting(UserDto::getPassword).are(conditionOf(StringUtils::isEmpty));
        assertThat(response.getBody()).extracting(UserDto::getId).containsExactlyInAnyOrder(2L, 3L);
    }
}
