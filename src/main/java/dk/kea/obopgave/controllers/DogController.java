package dk.kea.obopgave.controllers;

import dk.kea.obopgave.repository.DogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogController {

    private DogRepository dogRepository;

    public DogController() {
        this.dogRepository = new DogRepository();
    }

    @GetMapping("/")
    public String readAll(Model model){
        model.addAttribute("doglist", dogRepository.readAll());
        return "index";
    }
}
