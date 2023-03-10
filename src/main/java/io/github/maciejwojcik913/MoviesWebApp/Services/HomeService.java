package io.github.maciejwojcik913.MoviesWebApp.Services;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private final String greetingDefault = "Welcome ";
    private final String nameDefault = "Guest!";

    public String prepareGreeting(String name) {
        String message = greetingDefault;
        if (name == null || name.isEmpty()) {
            message += nameDefault;
        } else {
            message += name;
        }
        return message;
    }
}
