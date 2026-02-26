package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Pet;
import at.spengergasse.spring_thymeleaf.entities.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/pet")
public class PetController {
    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/list")
    public String pet(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "petlist";
    }

    @GetMapping("/add")
    public String addPet(Model model) {
        model.addAttribute("pet", new Pet());
        return "add_pet";
    }

    @PostMapping("/add")
    public String addPet(@ModelAttribute("pet") Pet pet) {
        petRepository.save(pet);
        return  "redirect:/pet/list";
    }
}
















