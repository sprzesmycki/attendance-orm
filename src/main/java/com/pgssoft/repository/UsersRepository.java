package com.pgssoft.repository;

import com.pgssoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jpiecuch on 2017-02-13.
 */
public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findByRole(User.Role role);

}
