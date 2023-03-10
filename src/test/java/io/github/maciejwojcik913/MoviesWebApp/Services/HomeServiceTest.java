package io.github.maciejwojcik913.MoviesWebApp.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class HomeServiceTest {

    HomeService homeService;

    @BeforeEach
    void setUp() {
        homeService = new HomeService();
    }

    @Test
    void prepareGreetingShouldNotThrowExceptionWhenGivenNameIsNull() {
        // given when then
        assertDoesNotThrow(
                () -> homeService.prepareGreeting(null)
        );
    }

    @Test
    void prepareGreetingShouldReturnSomeDefaultMessageWhenGivenNameIsNull() {
        // given when
        String greeting = homeService.prepareGreeting(null);

        // then
        assertThat(greeting, is(not(emptyOrNullString())));
    }

    @Test
    void prepareGreetingShouldReturnSomeDefaultMessageWhenGivenNameIsEmpty() {
        // given when
        String greeting = homeService.prepareGreeting("");

        // then
        assertThat(greeting, is(not(emptyOrNullString())));
    }

    @Test
    void prepareGreetingShouldReturnGreetingMessageWhichContainsGivenName() {
        // given
        var name = "MyName";

        // when
        String greeting = homeService.prepareGreeting(name);

        // then
        assertAll(
                () -> assertThat(greeting, containsString(name)),
                () -> assertThat(greeting.length(), greaterThan(name.length()))
        );
    }

}