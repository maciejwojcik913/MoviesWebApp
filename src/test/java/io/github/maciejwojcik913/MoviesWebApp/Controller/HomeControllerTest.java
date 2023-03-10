package io.github.maciejwojcik913.MoviesWebApp.Controller;

import io.github.maciejwojcik913.MoviesWebApp.Services.HomeService;
import io.github.maciejwojcik913.MoviesWebApp.ViewsNames;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeService homeService;

    @Test
    void getWelcomeShouldReturnGreetingWithStatusOkAndViewHome() throws Exception {
        // given
        var testMessage = "Welcome John!";
        given(homeService.prepareGreeting(any())).willReturn(testMessage);

        // when then
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(testMessage)))
                .andExpect(view().name(ViewsNames.HOME_PAGE));
    }

    @Test
    void getWelcomeShouldReturnGreetingWithStatusOkAndViewHomeWithRequestedParameter() throws Exception {
        // given
        var welcome = "Welcome";
        var parameter = "Paul";
        given(homeService.prepareGreeting(anyString())).willReturn(welcome + " " + parameter + "!");

        // when then
        this.mockMvc.perform(get("/home?name=" + parameter))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(welcome + " " + parameter)))
                .andExpect(view().name(ViewsNames.HOME_PAGE))
                .andDo(print());
    }
}