package io.github.maciejwojcik913.MoviesWebApp.Controller;

import io.github.maciejwojcik913.MoviesWebApp.ViewsNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {

    @GetMapping
    public ModelAndView getWelcome() {
        var mav = new ModelAndView();
        mav.setViewName(ViewsNames.HOME_PAGE);
        mav.addObject("greeting", "Hello Guest");
        return mav;
    }
}
