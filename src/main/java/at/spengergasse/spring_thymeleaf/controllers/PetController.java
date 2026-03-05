package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Pet;
import at.spengergasse.spring_thymeleaf.entities.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/edit/{id}")
    public String editPet(@PathVariable int id, Model model) {
        Pet pet = petRepository.findById(id).orElseThrow();
        model.addAttribute("pet", pet);
        return "edit_pet"; // besser eigenes template fürs edit
    }

    @PostMapping("/edit/{id}")
    public String updatePet(@PathVariable int id, @ModelAttribute("pet") Pet pet) {
        // WICHTIG: ID setzen, damit JPA updatet statt neu erstellt
        pet.setId(id);
        petRepository.save(pet);
        return "redirect:/pet/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePet(@PathVariable int id) {
        petRepository.deleteById(id);
        return "redirect:/pet/list";
    }







}




















