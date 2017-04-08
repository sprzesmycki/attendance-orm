package com.pgssoft.IT;

import com.pgssoft.dto.UserDto;
import com.pgssoft.model.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ManagingUsersIT extends AbstractITTest {

    private static final String USERS_URL = "/users";
    private static final String USER_URL = USERS_URL + "/%d";

    @Test
    public void managingUser() {
        UserDto request = createUser();

        ResponseEntity<Long> response = restTemplate.postForEntity(USERS_URL, request, Long.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        checkIfCreated(response.getBody(), request);
        checkUpdate(response.getBody());
        delete(response.getBody());
    }

    private void delete(Long id) {
        restTemplate.delete(String.format(USER_URL, id));
        ResponseEntity<UserDto> response = restTemplate.getForEntity(String.format(USER_URL, id), UserDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void checkIfCreated(Long id, UserDto request) {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(String.format(USER_URL, id), UserDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        UserDto user = response.getBody();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getEmail()).isEqualTo(request.getEmail());
        assertThat(user.getFirstName()).isEqualTo(request.getFirstName());
        assertThat(user.getLastName()).isEqualTo(request.getLastName());
        assertThat(user.getRole()).isEqualTo(User.Role.STUDENT.name());
    }

    private void checkUpdate(Long id) {
        UserDto request = createUser();
        request.setLastName("Editov");
        request.setRole(User.Role.ADMIN.name());
        request.setId(id + 1);
        restTemplate.put(String.format(USER_URL, id), request);
        checkIfCreated(id, request);
    }

    private UserDto createUser() {
        UserDto request = new UserDto();
        request.setEmail("juraj.examplov@test.com");
        request.setFirstName("Juraj");
        request.setLastName("Examplov");
        request.setPassword("secret");
        request.setRole("ADMIN");
        return request;
    }
}
