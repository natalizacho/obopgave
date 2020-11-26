package dk.kea.obopgave.controllers;

import dk.kea.obopgave.repository.DogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    DogRepository DR = new DogRepository();

    @GetMapping("/")
    public String index (Model model){
        model.addAttribute("doglist", DR.readAll());
        return "index";
    }
}
