package com.pgssoft.IT;

import org.assertj.core.api.Condition;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractITTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected static <T> Condition<T> conditionOf(String description, Predicate<T> predicate) {
        return conditionOf(predicate).as(description);
    }

    protected static <T> Condition<T> conditionOf(Predicate<T> test) {
        return new Condition<T>() {
            @Override
            public boolean matches(T value) {
                return test.test(value);
            }
        };
    }
}
