package io.github.maciejwojcik913.MoviesWebApp.Controller;

import io.github.maciejwojcik913.MoviesWebApp.Services.HomeService;
import io.github.maciejwojcik913.MoviesWebApp.ViewsNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping
    public ModelAndView getWelcome(@RequestParam(name = "name", required = false) String name) {
        var mav = new ModelAndView(ViewsNames.HOME_PAGE);
        mav.addObject("greeting", homeService.prepareGreeting(name));
        return mav;
    }
}
